package com.final_project.e_commerce.common;

import lombok.Data;

@Data
public class Result {
    private String code;
    private Object data;
    private String message;

    public static Result successNoReturnType(String code) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage("success");
        return result;
    }

    public static Result successWithReturnType(String code,  Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setMessage("success");
        return result;
    }

    public static Result error(String code,  String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
