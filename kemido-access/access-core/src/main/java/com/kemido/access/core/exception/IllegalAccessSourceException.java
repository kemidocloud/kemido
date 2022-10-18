package com.kemido.access.core.exception;

import com.kemido.access.core.constants.AccessErrorCode;
import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 非法的外部访问参数类型错误 </p>
 */
public class IllegalAccessSourceException extends PlatformException {

    public IllegalAccessSourceException() {
        super();
    }

    public IllegalAccessSourceException(String message) {
        super(message);
    }

    public IllegalAccessSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessSourceException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(AccessErrorCode.ILLEGAL_ACCESS_SOURCE, "社交登录Source参数错误", HttpStatus.SC_PRECONDITION_FAILED);
    }
}
