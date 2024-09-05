package cn.lb.subject.applicaiton.dto;

import java.io.Serializable;

/**
 * 题目答案DTO
 *
 * @author RainSoul
 * @create 2024-09-05
 */
public class SubjectAnswerDTO implements Serializable {

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
