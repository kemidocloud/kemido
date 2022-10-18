package com.kemido.oauth2.authorization.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <p>Description: OAuth2 (Security) 表单登录 Token </p>
 *
 * @see UsernamePasswordAuthenticationToken
 */
public class OAuth2FormLoginAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public OAuth2FormLoginAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OAuth2FormLoginAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
