package com.kemido.oauth2.core.exception;

import org.springframework.security.authentication.AccountStatusException;

/**
 * <p>Description: 登录端点限制 </p>
 */
public class AccountEndpointLimitedException extends AccountStatusException {

    public AccountEndpointLimitedException(String msg) {
        super(msg);
    }

    public AccountEndpointLimitedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
