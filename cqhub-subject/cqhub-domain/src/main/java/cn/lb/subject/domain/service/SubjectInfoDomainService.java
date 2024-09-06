package cn.lb.subject.domain.service;

import cn.lb.subject.common.entity.PageResult;
import cn.lb.subject.domain.entity.SubjectInfoBO;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author RainSoul
 * @create 2024-09-06
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目信息
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> getContributeList();
}
