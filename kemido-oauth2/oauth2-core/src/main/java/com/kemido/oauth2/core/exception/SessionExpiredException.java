package com.kemido.oauth2.core.exception;

import org.springframework.security.authentication.AccountStatusException;

/**
 * <p>Description: 自定义 Session 已过期 </p>
 */
public class SessionExpiredException extends AccountStatusException {
    
    public SessionExpiredException(String msg) {
        super(msg);
    }

    public SessionExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
