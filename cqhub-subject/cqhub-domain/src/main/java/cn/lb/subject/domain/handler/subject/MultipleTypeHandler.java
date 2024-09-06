package cn.lb.subject.domain.handler.subject;

import cn.lb.subject.common.enums.SubjectInfoTypeEnum;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;

/**
 * @author RainSoul
 * @create 2024-09-06
 */
public class MultipleTypeHandler implements SubjectTypeHandler{
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {

    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        return null;
    }
}
