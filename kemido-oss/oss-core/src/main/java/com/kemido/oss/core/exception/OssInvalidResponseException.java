package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssInvalidResponseException </p>
 */
public class OssInvalidResponseException extends PlatformException {

    public OssInvalidResponseException() {
        super();
    }

    public OssInvalidResponseException(String message) {
        super(message);
    }

    public OssInvalidResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInvalidResponseException(Throwable cause) {
        super(cause);
    }

    protected OssInvalidResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_INVALID_RESPONSE, "对象存储返回无效的响应", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
