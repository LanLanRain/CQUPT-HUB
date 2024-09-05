package cn.lb.subject.applicaiton.convert;

import cn.lb.subject.applicaiton.dto.SubjectCategoryDTO;
import cn.lb.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目分类DTO转换器
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDTOTOBO(SubjectCategoryDTO subjectCategoryDTO);

    SubjectCategoryDTO convertBOTODTO(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryDTO> convertBOTODTOList(List<SubjectCategoryBO> subjectCategoryBOList);

}
