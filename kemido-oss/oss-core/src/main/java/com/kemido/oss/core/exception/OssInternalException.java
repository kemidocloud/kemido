package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssInternalException </p>
 */
public class OssInternalException extends PlatformException {

    public OssInternalException() {
        super();
    }

    public OssInternalException(String message) {
        super(message);
    }

    public OssInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInternalException(Throwable cause) {
        super(cause);
    }

    protected OssInternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_INTERNAL, "对象存储服务器内部错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
