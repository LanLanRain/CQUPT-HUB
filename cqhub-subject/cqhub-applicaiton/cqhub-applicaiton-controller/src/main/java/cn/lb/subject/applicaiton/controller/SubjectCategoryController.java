package cn.lb.subject.applicaiton.controller;

import cn.lb.subject.applicaiton.convert.SubjectCategoryDTOConverter;
import cn.lb.subject.applicaiton.dto.SubjectCategoryDTO;
import cn.lb.subject.common.entity.Result;
import cn.lb.subject.domain.entity.SubjectCategoryBO;
import cn.lb.subject.domain.service.SubjectCategoryDomainService;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-05
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 添加学科分类信息
     *
     * @param subjectCategoryDTO 学科分类数据传输对象，使用请求体传输
     * @return 返回操作结果，包含成功标志和消息
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.request:{}", JSON.toJSONString(subjectCategoryDTO));
            }

            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父级分类id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOTOBO(subjectCategoryDTO);

            subjectCategoryDomainService.add(subjectCategoryBO);

            return Result.success();
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}", e.getMessage(), e);
            return Result.fail("新增分类失败");
        }
    }

    /**
     * 查询主要分类
     *
     * @param subjectCategoryDTO 分类查询条件传输对象，用于接收前端传来的查询参数
     * @return 包含主题分类传输对象列表的Result对象，表示查询到的分类信息
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOTOBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertBOTODTOList(subjectCategoryBOList);

            return Result.success(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail("查询分类失败");
        }
    }


}
