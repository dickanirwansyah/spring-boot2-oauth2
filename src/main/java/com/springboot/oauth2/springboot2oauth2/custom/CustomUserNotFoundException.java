package com.springboot.oauth2.springboot2oauth2.custom;

import org.springframework.security.core.AuthenticationException;

public class CustomUserNotFoundException extends AuthenticationException{

    public CustomUserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomUserNotFoundException(String msg) {
        super(msg);
    }
}
