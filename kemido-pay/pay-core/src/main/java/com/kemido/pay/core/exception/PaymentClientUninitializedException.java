package com.kemido.pay.core.exception;

/**
 * <p>Description: 创建客户端 KemidoException </p>
 */
public class PaymentClientUninitializedException extends PaymentException {

    public PaymentClientUninitializedException() {
        super();
    }

    public PaymentClientUninitializedException(String message) {
        super(message);
    }

    public PaymentClientUninitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentClientUninitializedException(Throwable cause) {
        super(cause);
    }

    public PaymentClientUninitializedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
