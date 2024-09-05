package cn.lb.subject.domain.service;

import cn.lb.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * 岗位大类领域服务
 *
 * @author RainSoul
 * @create 2024-09-05
 */
public interface SubjectCategoryDomainService {
    /**
     * 新增分类
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    /**
     * 删除分类
     */
    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类及标签
     */
    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
