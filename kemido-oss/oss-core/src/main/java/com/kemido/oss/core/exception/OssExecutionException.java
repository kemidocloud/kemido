package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssExecutionException </p>
 */
public class OssExecutionException extends PlatformException {

    public OssExecutionException() {
        super();
    }

    public OssExecutionException(String message) {
        super(message);
    }

    public OssExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssExecutionException(Throwable cause) {
        super(cause);
    }

    protected OssExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_EXECUTION, "对象存储服务器异步执行错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
