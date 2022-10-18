package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: OssInterruptedException </p>
 */
public class OssInterruptedException extends PlatformException {

    public OssInterruptedException() {
        super();
    }

    public OssInterruptedException(String message) {
        super(message);
    }

    public OssInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssInterruptedException(Throwable cause) {
        super(cause);
    }

    protected OssInterruptedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_INTERRUPTED, "对象存储服务器异步执行中断错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
