package com.kemido.assistant.core.definition.exception;

import com.kemido.assistant.core.domain.Result;

/**
 * <p>Description: 自定义错误基础类 </p>
 */
public abstract class AbstractKemidoException extends RuntimeException implements KemidoException {

    public AbstractKemidoException() {
        super();
    }

    public AbstractKemidoException(String message) {
        super(message);
    }

    public AbstractKemidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractKemidoException(Throwable cause) {
        super(cause);
    }

    protected AbstractKemidoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<String> getResult() {
        Result<String> result = Result.failure();
        result.code(getFeedback().getCode());
        result.message(getFeedback().getMessage());
        result.status(getFeedback().getStatus());
        result.stackTrace(super.getStackTrace());
        result.detail(super.getMessage());
        return result;
    }
}
