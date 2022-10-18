package com.kemido.oauth2.core.exception;


import com.kemido.assistant.core.domain.Feedback;
import org.apache.http.HttpStatus;

/**
 * <p> Description : 非法加密Key KemidoException </p>
 */
public class IllegalSymmetricKeyException extends PlatformAuthenticationException {

    public IllegalSymmetricKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public IllegalSymmetricKeyException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(50103, "静态AES加密算法KEY非法", HttpStatus.SC_NOT_IMPLEMENTED);
    }
}
