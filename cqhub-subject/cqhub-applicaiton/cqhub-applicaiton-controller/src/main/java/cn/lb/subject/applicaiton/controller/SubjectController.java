package cn.lb.subject.applicaiton.controller;

import cn.lb.subject.applicaiton.convert.SubjectAnswerDTOConverter;
import cn.lb.subject.applicaiton.convert.SubjectInfoDTOConverter;
import cn.lb.subject.applicaiton.dto.SubjectInfoDTO;
import cn.lb.subject.common.entity.PageResult;
import cn.lb.subject.common.entity.Result;
import cn.lb.subject.domain.entity.SubjectAnswerBO;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.service.SubjectCategoryDomainService;
import cn.lb.subject.domain.service.SubjectInfoDomainService;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 刷题controller
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Slf4j
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectInfoDTOConverter subjectInfoDTOConverter;

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 添加题目信息
     *
     * @param subjectInfoDTO 题目信息数据传输对象，包含题目名称、难度、类型、分数、分类ID、标签ID等信息
     * @return 操作结果，表示是否成功添加题目信息
     */
    @GetMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.add.request:{}", JSON.toJSONString(subjectInfoDTO));
            }

            // 校验参数
            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()),
                    "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()), "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()), "标签id不能为空");

            // 将DTO（数据传输对象）转换为BO（业务对象）
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOTOB(subjectInfoDTO);

            // 将题目的选项列表DTO转换为业务对象列表
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerDTOConverter.INSTANCE.convertDTOTOBOList(subjectInfoDTO.getOptionList());

            // 将转换后的选项列表设置回题目业务对象
            subjectInfoBO.setOptionList(subjectAnswerBOList);

            // 调用领域服务进行题目信息的添加操作
            subjectInfoDomainService.add(subjectInfoBO);

            // 返回操作成功的结果
            return Result.success(true);
        } catch (Exception e) {
            log.error("SubjectController.add.error:{}", e.getMessage(), e);
            return Result.fail("添加题目信息失败");
        }
    }

    /**
     * 根据分类ID和标签ID获取题目分页信息
     *
     * @param subjectInfoDTO 包含分类ID和标签ID的查询条件的DTO对象
     * @return 包含题目信息分页结果的Result对象
     */
    @PostMapping("/getSubjectPage")
    public Result<List<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.request:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOTOB(subjectInfoDTO);

            // 设置分页信息
            subjectInfoBO.setPageNo(subjectInfoDTO.getPageNo());
            subjectInfoBO.setPageSize(subjectInfoDTO.getPageSize());

            // 调用业务服务获取题目分页信息
            PageResult<SubjectInfoBO> subjectPage = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.success(subjectPage);
        } catch (Exception e) {
            log.error("SubjectController.getSubjectPage.error:{}", e.getMessage(), e);
            return Result.fail("获取题目信息失败");
        }
    }

    /**
     * 使用POST方法查询科目信息
     *
     * @param subjectInfoDTO 包含查询条件的科目信息数据传输对象
     * @return 返回包含查询结果的Result对象，其中data是SubjectInfoDTO类型
     */
    @PostMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> querySubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.querySubjectInfo.request:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTOTOB(subjectInfoDTO);
            SubjectInfoBO querySubjectInfo = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            SubjectInfoDTO converted = SubjectInfoDTOConverter.INSTANCE.convertBOTODTO(querySubjectInfo);
            return Result.success(converted);
        } catch (Exception e) {
            log.error("SubjectController.querySubjectInfo.error:{}", e.getMessage(), e);
            return Result.fail("查询题目信息失败");
        }
    }


}
