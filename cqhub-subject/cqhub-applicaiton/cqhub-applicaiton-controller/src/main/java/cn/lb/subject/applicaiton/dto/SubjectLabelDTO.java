package cn.lb.subject.applicaiton.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 标签分类
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectLabelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 标签分类
     */
    private String labelName;

    /**
     * 排序
     */
    private Integer sortNum;
}
