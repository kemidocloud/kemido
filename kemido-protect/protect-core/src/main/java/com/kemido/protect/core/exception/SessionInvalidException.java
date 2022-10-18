package com.kemido.protect.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.protect.core.constants.ProtectErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Session 不可用错误 </p>
 */
public class SessionInvalidException extends PlatformException {

    public SessionInvalidException() {
        super();
    }

    public SessionInvalidException(String message) {
        super(message);
    }

    public SessionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionInvalidException(Throwable cause) {
        super(cause);
    }

    public SessionInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(ProtectErrorCode.SESSION_INVALID, "Session已过期，请刷新再试", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
