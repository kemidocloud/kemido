package com.kemido.oauth2.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 无法解析SocialType错误 </p>
 */
public class SocialCredentialsParameterBindingFailedException extends AuthenticationException {

    public SocialCredentialsParameterBindingFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SocialCredentialsParameterBindingFailedException(String msg) {
        super(msg);
    }
}
