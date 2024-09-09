package cn.lb.gateway.enums;

import lombok.Getter;

/**
 * @author RainSoul
 * @create 2024-09-08
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    public int code;

    private String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultCodeEnum getResultCodeEnum(Integer code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.code == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
