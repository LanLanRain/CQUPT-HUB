package cn.lb.subject.common.entity;

import cn.lb.subject.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 通用的API返回结果封装类
 *
 * @param <T> 泛型参数，用于支持返回数据的任意类型
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class Result<T> {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 创建一个表示成功的Result对象，并携带指定的数据
     *
     * @param data 任意类型的数据
     * @param <T>  数据的类型
     * @return 返回一个表示成功的Result对象
     */
    public static <T> Result success(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 创建一个表示成功的Result对象，不携带数据
     *
     * @return 返回一个表示成功的Result对象
     */
    public static <T> Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * 创建一个表示失败的Result对象，并携带指定的数据
     *
     * @param data 任意类型的数据
     * @param <T>  数据的类型
     * @return 返回一个表示失败的Result对象
     */
    public static <T> Result fail(T data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 创建一个表示失败的Result对象，不携带数据
     *
     * @return 返回一个表示失败的Result对象
     */
    public static <T> Result fail() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
        return result;
    }
}

