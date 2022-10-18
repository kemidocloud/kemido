package com.kemido.access.core.exception;

import com.kemido.access.core.constants.AccessErrorCode;
import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Access 配置错误 </p>
 */
public class AccessConfigErrorException extends PlatformException {

    public AccessConfigErrorException() {
        super();
    }

    public AccessConfigErrorException(String message) {
        super(message);
    }

    public AccessConfigErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessConfigErrorException(Throwable cause) {
        super(cause);
    }

    protected AccessConfigErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(AccessErrorCode.ACCESS_CONFIG_ERROR, "Access 模块配置错误", HttpStatus.SC_PRECONDITION_FAILED);
    }
}
