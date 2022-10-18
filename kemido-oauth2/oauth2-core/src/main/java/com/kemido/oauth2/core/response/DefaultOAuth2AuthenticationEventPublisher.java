package com.kemido.oauth2.core.response;

import com.kemido.oauth2.core.constants.OAuth2ErrorCodes;
import com.kemido.oauth2.core.exception.AccountEndpointLimitedException;
import com.kemido.oauth2.core.exception.SessionExpiredException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * <p>Description: 扩展的 DefaultAuthenticationEventPublisher </p>
 *
 * 支持 OAuth2AuthenticationException 解析
 */
public class DefaultOAuth2AuthenticationEventPublisher extends DefaultAuthenticationEventPublisher {

    public DefaultOAuth2AuthenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        super.publishAuthenticationFailure(convert(exception), authentication);
    }

    private AuthenticationException convert(AuthenticationException exception) {
        if (exception instanceof OAuth2AuthenticationException) {
            OAuth2AuthenticationException authenticationException = (OAuth2AuthenticationException) exception;
            OAuth2Error error = authenticationException.getError();

            switch (error.getErrorCode()) {
                case OAuth2ErrorCodes.ACCOUNT_EXPIRED:
                    return new AccountExpiredException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorCodes.CREDENTIALS_EXPIRED:
                    return new CredentialsExpiredException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorCodes.ACCOUNT_DISABLED:
                    return new DisabledException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorCodes.ACCOUNT_LOCKED:
                    return new LockedException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorCodes.ACCOUNT_ENDPOINT_LIMITED:
                    return new AccountEndpointLimitedException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorCodes.USERNAME_NOT_FOUND:
                    return new UsernameNotFoundException(exception.getMessage(), exception.getCause());
                case OAuth2ErrorCodes.SESSION_EXPIRED:
                    return new SessionExpiredException(exception.getMessage(), exception.getCause());
                default:
                    return new BadCredentialsException(exception.getMessage(), exception.getCause());
            }
        }

        return exception;
    }
}
