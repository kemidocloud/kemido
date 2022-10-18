package com.kemido.pay.core.exception;

/**
 * <p>Description: 支付签名验证失败 </p>
 */
public class PaymentSignatureCheckErrorException extends PaymentException {

    public PaymentSignatureCheckErrorException() {
        super();
    }

    public PaymentSignatureCheckErrorException(String message) {
        super(message);
    }

    public PaymentSignatureCheckErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentSignatureCheckErrorException(Throwable cause) {
        super(cause);
    }

    public PaymentSignatureCheckErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
