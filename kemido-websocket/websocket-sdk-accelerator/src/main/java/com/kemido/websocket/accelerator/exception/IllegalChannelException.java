package com.kemido.websocket.accelerator.exception;

import com.kemido.websocket.core.exception.WebSocketException;

/**
 * <p>Description: Web Socket Channel 错误 </p>
 */
public class IllegalChannelException extends WebSocketException {

    public IllegalChannelException() {
        super();
    }

    public IllegalChannelException(String message) {
        super(message);
    }

    public IllegalChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalChannelException(Throwable cause) {
        super(cause);
    }

    public IllegalChannelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
