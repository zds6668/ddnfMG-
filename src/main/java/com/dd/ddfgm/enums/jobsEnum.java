package com.dd.ddfgm.enums;

public enum jobsEnum {
    SUCCESS(0, "ok"),
    FAILED(1, "notOk"),

    ;
    private Integer job;
    private String gameCareer;

    jobsEnum(int jod, String gameCareer) {
    }
}
