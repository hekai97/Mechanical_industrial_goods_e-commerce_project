package com.hekai.backend.entites.reConstruction.compositeEntities;

import java.io.Serializable;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
//包装结果
public class Result <T> {
    public static final int Normal=0;
    public static final int Error=1;
    private int status;
    private String message;
    private T data;

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


}
