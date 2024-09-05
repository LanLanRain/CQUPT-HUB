package cn.lb.subject.common.enums;

import lombok.Getter;

/**
 * 返回结果枚举类
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证"),
    NOT_FOUND(404, "接口不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    ;

    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultCodeEnum getByCode(Integer code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.getCode().equals(code)) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
