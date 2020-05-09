package com.dd.ddfgm.enums;

import lombok.Getter;

@Getter
public enum operatStatus {
    SUCCESS(0, "ok"),
    FAILED(1, "notOk"),

    ;
    private Integer status;
    private String msg;

    operatStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
