package com.kemido.cache.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.cache.core.constants.CacheErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Stamp签章 已过期错误 </p>
 */
public class StampHasExpiredException extends PlatformException {

    public StampHasExpiredException() {
        super();
    }

    public StampHasExpiredException(String message) {
        super(message);
    }

    public StampHasExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampHasExpiredException(Throwable cause) {
        super(cause);
    }

    protected StampHasExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(CacheErrorCode.STAMP_HAS_EXPIRED, "签章已过期", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
