package com.kemido.captcha.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 验证码为空 </p>
 */
public class CaptchaIsEmptyException extends PlatformException {

    public CaptchaIsEmptyException() {
        super();
    }

    public CaptchaIsEmptyException(String message) {
        super(message);
    }

    public CaptchaIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected CaptchaIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40611, "验证码不能为空", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
