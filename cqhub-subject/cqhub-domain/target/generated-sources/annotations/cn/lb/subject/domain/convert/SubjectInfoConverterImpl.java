package cn.lb.subject.domain.convert;

import cn.lb.subject.domain.entity.SubjectAnswerBO;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;
import cn.lb.subject.infra.basic.entity.SubjectInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-09T15:13:05+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class SubjectInfoConverterImpl implements SubjectInfoConverter {

    @Override
    public SubjectInfo convertBOTOEntity(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectInfo subjectInfo = new SubjectInfo();

        subjectInfo.setId( subjectInfoBO.getId() );
        subjectInfo.setSubjectName( subjectInfoBO.getSubjectName() );
        if ( subjectInfoBO.getSubjectDifficult() != null ) {
            subjectInfo.setSubjectDifficult( String.valueOf( subjectInfoBO.getSubjectDifficult() ) );
        }
        subjectInfo.setSettleName( subjectInfoBO.getSettleName() );
        subjectInfo.setSubjectType( subjectInfoBO.getSubjectType() );
        if ( subjectInfoBO.getSubjectScore() != null ) {
            subjectInfo.setSubjectScore( String.valueOf( subjectInfoBO.getSubjectScore() ) );
        }
        subjectInfo.setSubjectParse( subjectInfoBO.getSubjectParse() );
        subjectInfo.setSubjectCount( subjectInfoBO.getSubjectCount() );

        return subjectInfo;
    }

    @Override
    public SubjectInfoBO convertOptionBOTOSubjectInfoBO(SubjectOptionBO subjectOptionBO) {
        if ( subjectOptionBO == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setSubjectAnswer( subjectOptionBO.getSubjectAnswer() );
        List<SubjectAnswerBO> list = subjectOptionBO.getOptionList();
        if ( list != null ) {
            subjectInfoBO.setOptionList( new ArrayList<SubjectAnswerBO>( list ) );
        }

        return subjectInfoBO;
    }

    @Override
    public SubjectInfoBO convertOptionAndEntity2BO(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo) {
        if ( subjectOptionBO == null && subjectInfo == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        if ( subjectOptionBO != null ) {
            subjectInfoBO.setSubjectAnswer( subjectOptionBO.getSubjectAnswer() );
            List<SubjectAnswerBO> list = subjectOptionBO.getOptionList();
            if ( list != null ) {
                subjectInfoBO.setOptionList( new ArrayList<SubjectAnswerBO>( list ) );
            }
        }
        if ( subjectInfo != null ) {
            subjectInfoBO.setId( subjectInfo.getId() );
            subjectInfoBO.setSubjectName( subjectInfo.getSubjectName() );
            if ( subjectInfo.getSubjectDifficult() != null ) {
                subjectInfoBO.setSubjectDifficult( Integer.parseInt( subjectInfo.getSubjectDifficult() ) );
            }
            subjectInfoBO.setSettleName( subjectInfo.getSettleName() );
            subjectInfoBO.setSubjectType( subjectInfo.getSubjectType() );
            if ( subjectInfo.getSubjectScore() != null ) {
                subjectInfoBO.setSubjectScore( Integer.parseInt( subjectInfo.getSubjectScore() ) );
            }
            subjectInfoBO.setSubjectParse( subjectInfo.getSubjectParse() );
            subjectInfoBO.setSubjectCount( subjectInfo.getSubjectCount() );
        }

        return subjectInfoBO;
    }

    @Override
    public List<SubjectInfo> convertBOListTOEntityList(List<SubjectInfoBO> subjectInfoBOList) {
        if ( subjectInfoBOList == null ) {
            return null;
        }

        List<SubjectInfo> list = new ArrayList<SubjectInfo>( subjectInfoBOList.size() );
        for ( SubjectInfoBO subjectInfoBO : subjectInfoBOList ) {
            list.add( convertBOTOEntity( subjectInfoBO ) );
        }

        return list;
    }
}
