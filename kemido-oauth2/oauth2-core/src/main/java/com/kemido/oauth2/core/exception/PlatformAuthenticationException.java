package com.kemido.oauth2.core.exception;

import com.kemido.assistant.core.definition.exception.KemidoException;
import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.domain.Result;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 平台认证基础Exception </p>
 */
public class PlatformAuthenticationException extends AuthenticationException implements KemidoException {

    public PlatformAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PlatformAuthenticationException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return Feedback.ERROR;
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
