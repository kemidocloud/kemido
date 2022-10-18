package com.kemido.captcha.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 验证码已过期 </p>
 */
public class CaptchaHasExpiredException extends PlatformException {

    public CaptchaHasExpiredException() {
        super();
    }

    public CaptchaHasExpiredException(String message) {
        super(message);
    }

    public CaptchaHasExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaHasExpiredException(Throwable cause) {
        super(cause);
    }

    protected CaptchaHasExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40610, "验证码已过期", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
