package com.kemido.oauth2.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Oauth2 使用的验证码不匹配错误 </p>
 */
public class OAuth2CaptchaHasExpiredException extends OAuth2CaptchaException {

    public OAuth2CaptchaHasExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaHasExpiredException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40610, "验证码已过期", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
