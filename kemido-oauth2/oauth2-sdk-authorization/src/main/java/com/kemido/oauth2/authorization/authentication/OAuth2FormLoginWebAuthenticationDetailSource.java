package com.kemido.oauth2.authorization.authentication;

import com.kemido.oauth2.authorization.properties.OAuth2UiProperties;
import com.kemido.oauth2.core.definition.domain.FormLoginWebAuthenticationDetails;
import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: 表单登录 Details 定义 </p>
 */
public class OAuth2FormLoginWebAuthenticationDetailSource implements AuthenticationDetailsSource<HttpServletRequest, FormLoginWebAuthenticationDetails> {

    private final OAuth2UiProperties uiProperties;

    public OAuth2FormLoginWebAuthenticationDetailSource(OAuth2UiProperties uiProperties) {
        this.uiProperties = uiProperties;
    }

    @Override
    public FormLoginWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new FormLoginWebAuthenticationDetails(context, uiProperties.getCloseCaptcha(), uiProperties.getCaptchaParameter(), uiProperties.getCategory());
    }
}
