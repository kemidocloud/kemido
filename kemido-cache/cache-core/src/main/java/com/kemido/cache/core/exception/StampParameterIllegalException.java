package com.kemido.cache.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.cache.core.constants.CacheErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 请求参数中缺少幂等Token错误 </p>
 */
public class StampParameterIllegalException extends PlatformException {

    public StampParameterIllegalException() {
        super();
    }

    public StampParameterIllegalException(String message) {
        super(message);
    }

    public StampParameterIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampParameterIllegalException(Throwable cause) {
        super(cause);
    }

    protected StampParameterIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(CacheErrorCode.STAMP_PARAMETER_ILLEGAL, "缺少签章身份标记参数", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
