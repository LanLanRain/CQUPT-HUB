package cn.lb.subject.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目信息
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 题目名称
     */
    private String SubjectName;

    /**
     * 题目难度
     */
    private String SubjectDifficult;

    /**
     * 出题人名
     */
    private String settleName;

    /**
     * 题目类型 1:单选 2:多选 3:判断 4:简答
     */
    private Integer subjectType;

    /**
     * 题目分数
     */
    private String subjectScore;

    /**
     * 题目解析
     */
    private String subjectParse;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否删除 0:否 1:是
     */
    private Integer isDeleted;

    /**
     * 题目数量
     */
    private Integer SubjectCount;
}
