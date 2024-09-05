package cn.lb.subject.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * 题目点赞表 BO
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectLikedBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除状态 0未删除 1已删除
     */
    private Integer isDeleted;

}
