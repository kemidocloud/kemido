package com.kemido.oauth2.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Oauth2 使用的验证码不匹配错误 </p>
 */
public class OAuth2CaptchaMismatchException extends OAuth2CaptchaException {

    public OAuth2CaptchaMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaMismatchException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40612, "验证码不匹配", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
