package com.kemido.sms.core.exception;

/**
 * <p>Description: 短信模版参数顺序配置错误Exception </p>
 */
public class ParameterOrdersInvalidException extends SmsSendException {

    public ParameterOrdersInvalidException() {
    }

    public ParameterOrdersInvalidException(String message) {
        super(message);
    }

    public ParameterOrdersInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterOrdersInvalidException(Throwable cause) {
        super(cause);
    }

    public ParameterOrdersInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
