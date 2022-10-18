package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssInvalidKeyException </p>
 */
public class OssInvalidKeyException extends PlatformException {

    public OssInvalidKeyException() {
        super();
    }

    public OssInvalidKeyException(String message) {
        super(message);
    }

    public OssInvalidKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInvalidKeyException(Throwable cause) {
        super(cause);
    }

    protected OssInvalidKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_INVALID_KEY, "对象存储使用无效的秘钥", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
