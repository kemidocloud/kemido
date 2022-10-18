package com.kemido.sms.core.exception;

import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: 短信发送Exception </p>
 */
public class SmsSendException extends PlatformException {

    public SmsSendException() {
    }

    public SmsSendException(String message) {
        super(message);
    }

    public SmsSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsSendException(Throwable cause) {
        super(cause);
    }

    public SmsSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
