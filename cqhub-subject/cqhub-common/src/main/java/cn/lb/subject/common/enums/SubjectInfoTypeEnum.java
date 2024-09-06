package cn.lb.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举
 *
 * @author RainSoul
 * @create 2024-09-06
 */
@Getter
public enum SubjectInfoTypeEnum {
    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答"),
    ;

    private Integer code;
    private String desc;

    SubjectInfoTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(Integer code) {
        for (SubjectInfoTypeEnum subjectInfoTypeEnum : SubjectInfoTypeEnum.values()) {
            if (subjectInfoTypeEnum.getCode().equals(code)) {
                return subjectInfoTypeEnum;
            }
        }
        return null;
    }
}
