package com.kemido.protect.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.protect.core.constants.ProtectErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 操作频繁Exception </p>
 */
public class FrequentRequestsException extends IllegalOperationException {

    public FrequentRequestsException() {
    }

    public FrequentRequestsException(String message) {
        super(message);
    }

    public FrequentRequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrequentRequestsException(Throwable cause) {
        super(cause);
    }

    public FrequentRequestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(ProtectErrorCode.FREQUENT_REQUESTS, "请求频繁，请稍后再试", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
