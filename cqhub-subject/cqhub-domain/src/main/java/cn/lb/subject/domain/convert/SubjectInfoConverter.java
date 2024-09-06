package cn.lb.subject.domain.convert;

import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;
import cn.lb.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-06
 */
@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBOTOEntity(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertOptionBOTOSubjectInfoBO(SubjectOptionBO subjectOptionBO);

    SubjectInfoBO convertOptionAndEntity2BO(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);

    List<SubjectInfo> convertBOListTOEntityList(List<SubjectInfoBO> subjectInfoBOList);

}
