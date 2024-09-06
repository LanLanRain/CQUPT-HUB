package cn.lb.subject.domain.handler.subject;

import cn.lb.subject.common.enums.IsDeletedFlagEnum;
import cn.lb.subject.common.enums.SubjectInfoTypeEnum;
import cn.lb.subject.domain.convert.BriefSubjectConverter;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;
import cn.lb.subject.infra.basic.entity.SubjectBrief;
import cn.lb.subject.infra.basic.service.SubjectBriefService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 简答题处理器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId().intValue());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
