package com.example.mechanical_industrial_goods_eommerce_project_for_android.models;

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR");

    private final int code;
    private final String desc;

    private ResponseCode(int code, String desc) {
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
