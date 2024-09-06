package cn.lb.subject.domain.service;

import cn.lb.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 标签(SubjectLabel)表业务层接口
 *
 * @author RainSoul
 * @create 2024-09-06
 */
public interface SubjectLabelDomainService {

    /**
     * 新增标签
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查询分类下标签
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
