package cn.lb.subject.domain.handler.subject;

import cn.lb.subject.common.enums.SubjectInfoTypeEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目处理器工厂
 *
 * @author RainSoul
 * @create 2024-09-06
 */
public class SubjectTypeHandlerFactory implements InitializingBean{

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlers;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType){
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handlerMap.get(subjectInfoTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlers) {
            handlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        }
    }
}
