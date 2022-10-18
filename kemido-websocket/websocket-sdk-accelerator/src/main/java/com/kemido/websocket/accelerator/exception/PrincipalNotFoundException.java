package com.kemido.websocket.accelerator.exception;

import com.kemido.websocket.core.exception.WebSocketException;

/**
 * <p>Description: 无法找到 Principal 错误 </p>
 */
public class PrincipalNotFoundException extends WebSocketException {

    public PrincipalNotFoundException() {
        super();
    }

    public PrincipalNotFoundException(String message) {
        super(message);
    }

    public PrincipalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrincipalNotFoundException(Throwable cause) {
        super(cause);
    }

    public PrincipalNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
