package com.dd.ddfgm.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Five on 2020/7/8 1:14
 */
public class md5PasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equalsIgnoreCase(md5EncodeUtil.getMD5((String) rawPassword));
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return md5EncodeUtil.getMD5((String) rawPassword);
    }
}
