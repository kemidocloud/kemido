package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssServerException </p>
 */
public class OssServerException extends PlatformException {
    public OssServerException() {
        super();
    }

    public OssServerException(String message) {
        super(message);
    }

    public OssServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssServerException(Throwable cause) {
        super(cause);
    }

    protected OssServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_SERVER, "对象存储服务器出现错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
