package com.kemido.cache.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.cache.core.constants.CacheErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Stamp签章删除失败Exception </p>
 */
public class StampDeleteFailedException extends PlatformException {

    public StampDeleteFailedException() {
        super();
    }

    public StampDeleteFailedException(String message) {
        super(message);
    }

    public StampDeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampDeleteFailedException(Throwable cause) {
        super(cause);
    }

    protected StampDeleteFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(CacheErrorCode.STAMP_DELETE_FAILED, "从缓存中删除签章失败", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
