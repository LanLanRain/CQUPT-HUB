package cn.lb.auth.entity;

import lombok.Getter;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(String name) {
        for (ResultCodeEnum statusEnum : ResultCodeEnum.values()) {
            if (statusEnum.name().equals(name)) {
                return statusEnum.message;
            }
        }
        return null;
    }
}
