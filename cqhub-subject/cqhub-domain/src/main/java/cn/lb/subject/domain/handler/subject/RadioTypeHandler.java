package cn.lb.subject.domain.handler.subject;

import cn.lb.subject.common.enums.IsDeletedFlagEnum;
import cn.lb.subject.common.enums.SubjectInfoTypeEnum;
import cn.lb.subject.domain.convert.RadioSubjectConverter;
import cn.lb.subject.domain.entity.SubjectAnswerBO;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;
import cn.lb.subject.infra.basic.entity.SubjectRadio;
import cn.lb.subject.infra.basic.service.SubjectRadioService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 单选题处理器
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    /**
     * 向数据库中批量添加科目选项信息
     *
     * @param subjectInfoBO 科目信息业务对象，包含科目选项等信息
     */
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectRadio> subjectRadioList = new LinkedList<>();

        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertDTOToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETE.getCode());
            subjectRadioList.add(subjectRadio);
        })  ;

        subjectRadioService.batchInsert(subjectRadioList);
    }


    @Override
    /**
     * 根据题目ID查询题目选项
     *
     * @param subjectId 题目ID
     * @return 返回封装了题目选项的SubjectOptionBO对象
     */
    public SubjectOptionBO query(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(Long.valueOf(subjectId));
        List<SubjectRadio> subjectRadios = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = RadioSubjectConverter.INSTANCE.convertEntityToBOList(subjectRadios);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
