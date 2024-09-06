package cn.lb.subject.applicaiton.controller;

import cn.lb.subject.applicaiton.dto.SubjectLikedDTO;
import cn.lb.subject.common.entity.Result;
import cn.lb.subject.domain.service.SubjectLikedDomainService;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题目点赞controller
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Slf4j
@RestController
@RequestMapping("/subjectLiked/")
public class SubjectLikedController {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLikedController.add.dto:{}", JSON.toJSONString(subjectLikedDTO));
        }

        Preconditions.checkNotNull(subjectLikedDTO.getSubjectId(), "题目id不能为空");
        Preconditions.checkNotNull(subjectLikedDTO.getStatus(), "点赞状态不能为空");
        return Result.success();
    }
}
