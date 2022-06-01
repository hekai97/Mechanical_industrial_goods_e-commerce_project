package com.hekai.backend.entites.reConstruction.compositeEntities;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
//包装结果

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result <T>{
    public static final int Normal=0;
    public static final int Error=1;
    private int status;
    private String msg;
    private T data;
    public Result(){}

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, String message) {
        this.status = status;
        this.msg = message;
    }

    public Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public Result(int status, String message, T data) {
        this.status = status;
        this.msg = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> createRespBySuccess() {
        return new Result<>(StatusCode.SUCCESS.getCode());
    }

    public static <T> Result<T> createRespBySuccess(T data) {
        return new Result<>(StatusCode.SUCCESS.getCode(),data);
    }

    public static <T> Result<T> createRespBySuccess(String msg, T data) {
        return new Result<>(StatusCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> createRespBySuccessMessage(String msg) {
        return new Result<>(StatusCode.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> createRespByError() {
        return new Result<>(StatusCode.ERROR.getCode(), StatusCode.ERROR.getDesc());
    }

    public static <T> Result<T> createByErrorMessage(String errorMessage) {
        return new Result<T>(StatusCode.ERROR.getCode(), errorMessage);
    }

    public static <T> Result<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new Result<T>(errorCode, errorMessage);
    }
}
