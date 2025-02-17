package com.chenpp.deepadmin.common;

import lombok.Data;

import java.util.List;

/**
 * @author April.Chen
 * @date 2025/2/14 10:14
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;


    public static Result<?> success() {
        Result<List<?>> result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result<Object> success(Object data) {
        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public Result<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public static Result<?> error() {
        Result<List<?>> result = new Result<>();
        result.setCode(500);
        return result;
    }
}
