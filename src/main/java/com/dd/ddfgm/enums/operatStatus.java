package com.dd.ddfgm.enums;

import lombok.Getter;

@Getter
public enum operatStatus implements CodeEnum{
    SUCCESS(0, "ok"),
    FAILED(1, "notOk"),

    ;
    private Integer status;
    private String msg;

    operatStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getCode() {
        return status;
    }
}
