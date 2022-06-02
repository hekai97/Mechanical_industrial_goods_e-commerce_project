package com.hekai.backend.entites.reConstruction.compositeEntities;

/**
 * @author: hekai
 * @Date: 2022/5/31
 */
public enum StatusCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    UN_LOGIN(2,"UNLOGIN");

    private final int code;
    private final String desc;

    private StatusCode(int code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
