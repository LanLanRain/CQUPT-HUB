package cn.lb.subject.infra.basic.service;

import cn.lb.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
 * 题目单选题表(SubjectRadio)表服务接口
 *
 * @author RainSoul
 * @create 2024-09-06
 */
public interface SubjectRadioService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio insert(SubjectRadio subjectRadio);

    /**
     * 批量插入
     */
    void batchInsert(List<SubjectRadio> subjectRadioList);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio update(SubjectRadio subjectRadio);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 条件查询
     * @param subjectRadio
     * @return
     */
    List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio);
}
