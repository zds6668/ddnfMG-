package com.dd.ddfgm.exception;

/**
 * Created by Five on 2020/7/8 21:00
 */

import org.springframework.security.core.AuthenticationException;

/**
 * 声明一个验证码异常，用于抛出特定的验证码异常
 */
public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException(String msg) {
        super(msg);
    }
}
