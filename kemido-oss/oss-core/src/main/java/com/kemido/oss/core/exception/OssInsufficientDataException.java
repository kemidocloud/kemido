package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssInsufficientDataException </p>
 */
public class OssInsufficientDataException extends PlatformException {

    public OssInsufficientDataException() {
        super();
    }

    public OssInsufficientDataException(String message) {
        super(message);
    }

    public OssInsufficientDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInsufficientDataException(Throwable cause) {
        super(cause);
    }

    protected OssInsufficientDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_INSUFFICIENT_DATA, "对象存储服务器返回数据不足", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
