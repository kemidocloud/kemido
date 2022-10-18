package com.kemido.pay.core.exception;

/**
 * <p>Description: 支付配置Id错误 </p>
 */
public class PaymentProfileIdIncorrectException extends PaymentException {

    public PaymentProfileIdIncorrectException() {
        super();
    }

    public PaymentProfileIdIncorrectException(String message) {
        super(message);
    }

    public PaymentProfileIdIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentProfileIdIncorrectException(Throwable cause) {
        super(cause);
    }

    public PaymentProfileIdIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
