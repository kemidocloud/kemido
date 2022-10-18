package com.kemido.oauth2.authorization.exception;

import com.kemido.assistant.core.domain.Feedback;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 验证码为空 </p>
 */
public class OAuth2CaptchaIsEmptyException extends OAuth2CaptchaException {

    public OAuth2CaptchaIsEmptyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaIsEmptyException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40611, "验证码不能为空", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
