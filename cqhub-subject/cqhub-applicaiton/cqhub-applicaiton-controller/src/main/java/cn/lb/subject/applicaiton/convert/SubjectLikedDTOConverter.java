package cn.lb.subject.applicaiton.convert;

import cn.lb.subject.applicaiton.dto.SubjectLikedDTO;
import cn.lb.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 题目点赞记录DTO转换器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Mapper
public interface SubjectLikedDTOConverter {
    SubjectLikedDTOConverter INSTANCE = Mappers.getMapper(SubjectLikedDTOConverter.class);

    SubjectLikedBO convertDTOTOBO(SubjectLikedDTO subjectLikedDTO);
}
