package cn.lb.subject.domain.handler.subject;

import cn.lb.subject.common.enums.SubjectInfoTypeEnum;
import cn.lb.subject.domain.entity.SubjectInfoBO;
import cn.lb.subject.domain.entity.SubjectOptionBO;

/**
 * 题目处理器接口
 *
 * @author RainSoul
 * @create 2024-09-06
 */
public interface SubjectTypeHandler {

    /**
     * 获取处理器类型
     */
   SubjectInfoTypeEnum getHandlerType();

   /**
    * 添加题目
    */
   void add(SubjectInfoBO subjectInfoBO);

   /**
    * 查询题目信息
    */
   SubjectOptionBO query(SubjectInfoBO subjectInfoBO);

}
