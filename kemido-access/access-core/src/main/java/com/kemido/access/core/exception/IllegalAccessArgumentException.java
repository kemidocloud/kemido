package com.kemido.access.core.exception;

import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: 非法的访问参数错误 </p>
 */
public class IllegalAccessArgumentException extends PlatformException {

    public IllegalAccessArgumentException() {
        super();
    }

    public IllegalAccessArgumentException(String message) {
        super(message);
    }

    public IllegalAccessArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
