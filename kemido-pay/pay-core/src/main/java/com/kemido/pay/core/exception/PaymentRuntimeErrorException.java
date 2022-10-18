package com.kemido.pay.core.exception;

/**
 * <p>Description: 支持处理错误 </p>
 */
public class PaymentRuntimeErrorException extends PaymentException {

    public PaymentRuntimeErrorException() {
        super();
    }

    public PaymentRuntimeErrorException(String message) {
        super(message);
    }

    public PaymentRuntimeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentRuntimeErrorException(Throwable cause) {
        super(cause);
    }

    public PaymentRuntimeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
