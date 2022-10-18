package com.kemido.pay.core.exception;

/**
 * <p>Description: Profile 未找到 KemidoException </p>
 */
public class PaymentProfileNotFoundException extends PaymentException {

    public PaymentProfileNotFoundException() {
        super();
    }

    public PaymentProfileNotFoundException(String message) {
        super(message);
    }

    public PaymentProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentProfileNotFoundException(Throwable cause) {
        super(cause);
    }

    public PaymentProfileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
