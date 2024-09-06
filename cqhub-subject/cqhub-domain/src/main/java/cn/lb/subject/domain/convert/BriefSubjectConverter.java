package cn.lb.subject.domain.convert;

import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 简答题转换器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Mapper
public interface BriefSubjectConverter {

    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);
}
