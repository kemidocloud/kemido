package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssNoSuchAlgorithmException </p>
 */
public class OssNoSuchAlgorithmException extends PlatformException {

    public OssNoSuchAlgorithmException() {
        super();
    }

    public OssNoSuchAlgorithmException(String message) {
        super(message);
    }

    public OssNoSuchAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssNoSuchAlgorithmException(Throwable cause) {
        super(cause);
    }

    protected OssNoSuchAlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_NO_SUCH_ALGORITHM, "使用对象存储不支持算法错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
