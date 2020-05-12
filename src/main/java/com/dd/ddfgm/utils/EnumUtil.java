package com.dd.ddfgm.utils;

import com.dd.ddfgm.enums.CodeEnum;
import com.dd.ddfgm.enums.JobEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    public static <T extends JobEnum> T getByCode(String job, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (job.equalsIgnoreCase(each.getJob())) {
                return each;
            }
        }
        return null;
    }
}
