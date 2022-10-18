package com.kemido.assistant.core.exception;

import com.kemido.assistant.core.definition.exception.AbstractKemidoException;
import com.kemido.assistant.core.domain.Feedback;

/**
 * <p>Description: 平台基础Exception </p>
 */
public class PlatformException extends AbstractKemidoException {

    public PlatformException() {
        super();
    }

    public PlatformException(String message) {
        super(message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }

    protected PlatformException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return Feedback.ERROR;
    }
}
