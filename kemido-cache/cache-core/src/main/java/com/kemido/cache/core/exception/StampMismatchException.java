package com.kemido.cache.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.cache.core.constants.CacheErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Stamp签章校验错误 </p>
 */
public class StampMismatchException extends PlatformException {

    public StampMismatchException() {
        super();
    }

    public StampMismatchException(String message) {
        super(message);
    }

    public StampMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampMismatchException(Throwable cause) {
        super(cause);
    }

    protected StampMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(CacheErrorCode.STAMP_MISMATCH, "签章信息无法匹配", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
