package com.lph.exam_web.common;

public enum Code {
    SUCCESS(200,"success"),
    ERROR(404,"error");

    private final int code;
    private final String desc;

    Code(int code, String desc) {
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
