package cn.lb.subject.domain.convert;

import cn.lb.subject.domain.entity.SubjectCategoryBO;
import cn.lb.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 岗位大类转换器
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Mapper
public interface SubjectCategoryConverter {
    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    SubjectCategory convertBOTOEntity(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertCategoryListTOBO(List<SubjectCategory> subjectCategoryList);
}
