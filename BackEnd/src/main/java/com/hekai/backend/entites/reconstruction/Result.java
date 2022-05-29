package com.hekai.backend.entites.reconstruction;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
//包装结果
public class Result <T>{
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int status;
    private String message;
    private T data;

}
