package com.shuyu.springcloud.auth.config.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码不加密处理
 */
public class NoPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }

}