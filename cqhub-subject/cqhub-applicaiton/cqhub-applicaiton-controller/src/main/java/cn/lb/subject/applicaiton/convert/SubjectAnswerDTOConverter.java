package cn.lb.subject.applicaiton.convert;

import cn.lb.subject.applicaiton.dto.SubjectAnswerDTO;
import cn.lb.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目答案DTO转换器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDTOTOBO(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertDTOTOBOList(List<SubjectAnswerDTO> subjectAnswerDTOList);
}
