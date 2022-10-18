package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssIOException </p>
 */
public class OssIOException extends PlatformException {

    public OssIOException() {
        super();
    }

    public OssIOException(String message) {
        super(message);
    }

    public OssIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssIOException(Throwable cause) {
        super(cause);
    }

    protected OssIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_IO, "对象存储出现IO错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
