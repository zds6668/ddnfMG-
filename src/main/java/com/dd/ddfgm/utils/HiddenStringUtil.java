package com.dd.ddfgm.utils;

import java.util.Random;

/**
 * Created by Five on 2020/7/14 2:15
 */
public class HiddenStringUtil {
    public static String hiddenStringRandom(String name) {
        int len = name.length();
        int index;
        StringBuilder sb = new StringBuilder(name);
        for (int i = 0; i < len / 2; i++) {
            index = new Random().nextInt(len);
            sb.replace(index, index + 1, "*");
        }
        return sb.toString();
    }

    public static String hiddenStringHalf(String name) {
        int len = name.length();
        StringBuilder sb = new StringBuilder(name);
        sb.replace(0, len/2 + 1, "@");
        return "加密处理" + sb.toString();
    }
}
