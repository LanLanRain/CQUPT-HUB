package cn.lb.subject.domain.handler.subject;

import cn.lb.subject.common.enums.SubjectInfoTypeEnum;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

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

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {

    }

    @Override
    public SubjectOptionBO query(SubjectInfoBO subjectInfoBO) {
        return null;
    }
}
