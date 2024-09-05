package cn.lb.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签BO
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectLabelBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 标签分类
     */
    private String labelName;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 分类id
     */
    private Long categoryId;
}
