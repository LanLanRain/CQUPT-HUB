package cn.lb.subject.common.enums;

import lombok.Getter;

/**
 * 是否删除标识枚举
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Getter
public enum IsDeletedFlagEnum {
    DELETE(1, "已删除"),
    UN_DELETE(0, "未删除");

    private Integer code;

    private String message;

    IsDeletedFlagEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static IsDeletedFlagEnum getByCode(Integer code) {
        for (IsDeletedFlagEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
