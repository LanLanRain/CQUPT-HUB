package cn.lb.subject.domain.entity;

import lombok.Data;

/**
 * 题目答案BO
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectAnswerBO {
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
