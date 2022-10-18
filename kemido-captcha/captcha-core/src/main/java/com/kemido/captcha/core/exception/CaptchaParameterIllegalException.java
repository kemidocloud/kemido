package com.kemido.captcha.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 验证码校验参数错误 </p>
 */
public class CaptchaParameterIllegalException extends PlatformException {

    public CaptchaParameterIllegalException() {
        super();
    }

    public CaptchaParameterIllegalException(String message) {
        super(message);
    }

    public CaptchaParameterIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaParameterIllegalException(Throwable cause) {
        super(cause);
    }

    protected CaptchaParameterIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40613, "验证码参数格式错误", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
