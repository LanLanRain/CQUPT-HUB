package cn.lb.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案BO
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectAnswerBO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 答案选项标识
     */
    private Integer optionType;

    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}
