package com.kemido.oss.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.oss.core.constants.OssErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 获取从连接池中获取Minio客户端错误 </p>
 */
public class OssClientPoolErrorException extends PlatformException {

    public OssClientPoolErrorException() {
        super();
    }

    public OssClientPoolErrorException(String message) {
        super(message);
    }

    public OssClientPoolErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public OssClientPoolErrorException(Throwable cause) {
        super(cause);
    }

    protected OssClientPoolErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(OssErrorCode.OSS_CLIENT_POOL_ERROR, "无法从Oss对象池中获取对象", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
