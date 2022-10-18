package com.kemido.protect.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.protect.core.constants.ProtectErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 重复提交Exception </p>
 */
public class RepeatSubmissionException extends IllegalOperationException {

    public RepeatSubmissionException() {
    }

    public RepeatSubmissionException(String message) {
        super(message);
    }

    public RepeatSubmissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatSubmissionException(Throwable cause) {
        super(cause);
    }

    public RepeatSubmissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return new Feedback(ProtectErrorCode.REPEAT_SUBMISSION, "提交进行中，请不要重复提交", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
