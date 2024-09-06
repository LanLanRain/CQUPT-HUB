package cn.lb.subject.applicaiton.convert;

import cn.lb.subject.applicaiton.dto.SubjectInfoDTO;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目信息DTO转换器
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOTOB(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBOTODTO(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoDTO> convertBOTODTOList(List<SubjectInfoBO> subjectInfoBOList);
}
