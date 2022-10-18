package com.kemido.access.core.exception;

import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: 接入身份认证错误 </p>
 */
public class AccessIdentityVerificationFailedException extends PlatformException {

    public AccessIdentityVerificationFailedException() {
        super();
    }

    public AccessIdentityVerificationFailedException(String message) {
        super(message);
    }

    public AccessIdentityVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessIdentityVerificationFailedException(Throwable cause) {
        super(cause);
    }

    public AccessIdentityVerificationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
