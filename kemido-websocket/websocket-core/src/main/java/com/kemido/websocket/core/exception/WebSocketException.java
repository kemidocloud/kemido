package com.kemido.websocket.core.exception;

import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: WebSocket KemidoException  </p>
 */
public class WebSocketException extends PlatformException {

    public WebSocketException() {
        super();
    }

    public WebSocketException(String message) {
        super(message);
    }

    public WebSocketException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebSocketException(Throwable cause) {
        super(cause);
    }

    public WebSocketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
