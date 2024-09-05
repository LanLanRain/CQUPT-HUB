package cn.lb.subject.infra.basic.service;

import cn.lb.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务接口
 *
 * @author RainSoul
 * @create 2024-09-05
 */
public interface SubjectCategoryService {
    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    SubjectCategory insert(SubjectCategory subjectCategory);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectCategory queryById(Long id);

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    int update(SubjectCategory subjectCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询岗位大类
     *
     * @param subjectCategory
     * @return
     */
    List<SubjectCategory> queryCategory(SubjectCategory subjectCategory);

    /**
     * 查询岗位大类下的题目数量
     *
     * @param id
     * @return
     */
    Integer querySubjectCount(Long id);
}
