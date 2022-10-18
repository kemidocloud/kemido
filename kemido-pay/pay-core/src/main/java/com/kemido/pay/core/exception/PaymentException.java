package com.kemido.pay.core.exception;

import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: 支付基础Exception </p>
 */
public class PaymentException extends PlatformException {

    public PaymentException() {
        super();
    }

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentException(Throwable cause) {
        super(cause);
    }

    public PaymentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
