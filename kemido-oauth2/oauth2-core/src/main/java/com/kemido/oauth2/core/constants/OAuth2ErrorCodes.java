package com.kemido.oauth2.core.constants;

/**
 * <p>Description: 扩展 OAuth2 错误代码 </p>
 */
public interface OAuth2ErrorCodes extends org.springframework.security.oauth2.core.OAuth2ErrorCodes {

    String ACCOUNT_EXPIRED = "AccountExpiredException";
    String ACCOUNT_DISABLED = "DisabledException";
    String ACCOUNT_LOCKED = "LockedException";
    String ACCOUNT_ENDPOINT_LIMITED = "AccountEndpointLimitedException";
    String BAD_CREDENTIALS = "BadCredentialsException";
    String CREDENTIALS_EXPIRED = "CredentialsExpiredException";
    String USERNAME_NOT_FOUND = "UsernameNotFoundException";

    String SESSION_EXPIRED = "SessionExpiredException";

}
