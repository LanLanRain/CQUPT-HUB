package cn.lb.subject.domain.convert;

import cn.lb.subject.domain.entity.SubjectAnswerBO;
import cn.lb.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 多选题转换器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Mapper
public interface MultipleSubjectConverter {
    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertAnswerBOToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToAnswerBOList(List<SubjectMultiple> subjectMultipleList);
}
