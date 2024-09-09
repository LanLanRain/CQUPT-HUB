package cn.lb.gateway.entity;

import cn.lb.gateway.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 统一返回结果
 *
 * @author RainSoul
 * @create 2024-09-08
 */
@Data
public class Result<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static <T> Result success() {
        Result result = new Result();

        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result success(T data) {
        Result result = new Result();

        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result fail() {
        Result result = new Result();

        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.code);
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
        return result;
    }

    public static <T> Result fail(Integer code, String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result fail(T data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.code);
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
        result.setData(data);
        return result;
    }
}
