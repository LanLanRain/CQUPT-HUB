package cn.lb.subject.domain.convert;

import cn.lb.subject.domain.entity.SubjectAnswerBO;
import cn.lb.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 单选题转换器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Mapper
public interface RadioSubjectConverter {

    RadioSubjectConverter INSTANCE = Mappers.getMapper(RadioSubjectConverter.class);

    SubjectRadio convertDTOToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToBOList(List<SubjectRadio> subjectRadioList);
}
