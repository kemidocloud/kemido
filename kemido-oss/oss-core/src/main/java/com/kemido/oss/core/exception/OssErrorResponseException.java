package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Oss 响应错误 </p>
 */
public class OssErrorResponseException extends PlatformException {

    public OssErrorResponseException() {
        super();
    }

    public OssErrorResponseException(String message) {
        super(message);
    }

    public OssErrorResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssErrorResponseException(Throwable cause) {
        super(cause);
    }

    protected OssErrorResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_ERROR_RESPONSE, "对象存储服务器返回错误响应", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
