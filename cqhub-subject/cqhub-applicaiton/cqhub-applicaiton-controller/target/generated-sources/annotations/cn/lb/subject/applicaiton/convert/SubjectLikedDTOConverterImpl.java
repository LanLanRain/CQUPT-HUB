package cn.lb.subject.applicaiton.convert;

import cn.lb.subject.applicaiton.dto.SubjectLikedDTO;
import cn.lb.subject.domain.entity.SubjectLikedBO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-09T15:23:12+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class SubjectLikedDTOConverterImpl implements SubjectLikedDTOConverter {

    @Override
    public SubjectLikedBO convertDTOTOBO(SubjectLikedDTO subjectLikedDTO) {
        if ( subjectLikedDTO == null ) {
            return null;
        }

        SubjectLikedBO subjectLikedBO = new SubjectLikedBO();

        subjectLikedBO.setId( subjectLikedDTO.getId() );
        subjectLikedBO.setSubjectId( subjectLikedDTO.getSubjectId() );
        subjectLikedBO.setSubjectName( subjectLikedDTO.getSubjectName() );
        subjectLikedBO.setLikeUserId( subjectLikedDTO.getLikeUserId() );
        subjectLikedBO.setStatus( subjectLikedDTO.getStatus() );
        subjectLikedBO.setCreatedBy( subjectLikedDTO.getCreatedBy() );
        subjectLikedBO.setCreatedTime( subjectLikedDTO.getCreatedTime() );
        subjectLikedBO.setUpdateBy( subjectLikedDTO.getUpdateBy() );
        subjectLikedBO.setUpdateTime( subjectLikedDTO.getUpdateTime() );
        subjectLikedBO.setIsDeleted( subjectLikedDTO.getIsDeleted() );

        return subjectLikedBO;
    }
}
