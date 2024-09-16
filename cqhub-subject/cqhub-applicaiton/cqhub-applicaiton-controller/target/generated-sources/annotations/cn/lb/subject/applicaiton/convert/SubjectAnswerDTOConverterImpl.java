package cn.lb.subject.applicaiton.convert;

import cn.lb.subject.applicaiton.dto.SubjectAnswerDTO;
import cn.lb.subject.domain.entity.SubjectAnswerBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-09T15:23:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public SubjectAnswerBO convertDTOTOBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    @Override
    public List<SubjectAnswerBO> convertDTOTOBOList(List<SubjectAnswerDTO> subjectAnswerDTOList) {
        if ( subjectAnswerDTOList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectAnswerDTOList.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : subjectAnswerDTOList ) {
            list.add( convertDTOTOBO( subjectAnswerDTO ) );
        }

        return list;
    }
}
