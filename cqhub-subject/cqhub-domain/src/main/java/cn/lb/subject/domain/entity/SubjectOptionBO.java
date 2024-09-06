package cn.lb.subject.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * 题目答案BO
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class SubjectOptionBO {
    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;
}
