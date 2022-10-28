package com.kemido.oauth2.core.exception;

import com.kemido.oauth2.core.exception.PlatformAuthenticationException;

/**
 * <p>Description: 社交登录绑定用户出错 </p>
 */
public class SocialCredentialsUserBindingFailedException extends PlatformAuthenticationException {

    public SocialCredentialsUserBindingFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SocialCredentialsUserBindingFailedException(String msg) {
        super(msg);
    }
}
