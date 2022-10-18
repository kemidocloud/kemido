package com.kemido.oauth2.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import org.apache.http.HttpStatus;

/**
 * <p>Description: UsernameAlreadyExistsException </p>
 */
public class UsernameAlreadyExistsException extends PlatformAuthenticationException {

    public UsernameAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(40614, "静态AES加密算法KEY非法", HttpStatus.SC_NOT_ACCEPTABLE);
    }
}
