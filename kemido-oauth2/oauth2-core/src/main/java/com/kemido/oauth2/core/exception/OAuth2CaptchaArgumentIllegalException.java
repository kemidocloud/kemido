package com.kemido.oauth2.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Oauth2 使用的验证码参数错误 </p>
 */
public class OAuth2CaptchaArgumentIllegalException extends OAuth2CaptchaException {

    public OAuth2CaptchaArgumentIllegalException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaArgumentIllegalException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40613, "验证码参数格式错误", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
