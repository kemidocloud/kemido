package com.kemido.access.core.exception;

import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: 接入预操作失败错误 </p>
 */
public class AccessPreProcessFailedException extends PlatformException {

    public AccessPreProcessFailedException() {
    }

    public AccessPreProcessFailedException(String message) {
        super(message);
    }

    public AccessPreProcessFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessPreProcessFailedException(Throwable cause) {
        super(cause);
    }

    public AccessPreProcessFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
