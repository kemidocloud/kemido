package com.kemido.oauth2.authorization.authorization;

import com.kemido.captcha.core.processor.CaptchaRendererFactory;
import com.kemido.oauth2.authorization.properties.OAuth2UiProperties;
import com.kemido.oauth2.authorization.authentication.OAuth2FormLoginAuthenticationFailureHandler;
import com.kemido.oauth2.authorization.authentication.OAuth2FormLoginAuthenticationFilter;
import com.kemido.oauth2.authorization.authentication.OAuth2FormLoginAuthenticationProvider;
import com.kemido.oauth2.authorization.authentication.OAuth2FormLoginWebAuthenticationDetailSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>Description: OAuth2 Form Login Configurer </p>
 *
 * 使用此种方式，相当于额外增加了一种表单登录方式。因此对原有的 http.formlogin进行的配置，对当前此种方式的配置并不生效。
 *
 * @see org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer
 */
public class OAuth2FormLoginConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2FormLoginConfigurer.class);

    private final UserDetailsService userDetailsService;
    private final OAuth2UiProperties uiProperties;
    private final CaptchaRendererFactory captchaRendererFactory;

    public OAuth2FormLoginConfigurer(UserDetailsService userDetailsService, OAuth2UiProperties uiProperties, CaptchaRendererFactory captchaRendererFactory) {
        this.userDetailsService = userDetailsService;
        this.uiProperties = uiProperties;
        this.captchaRendererFactory = captchaRendererFactory;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);

        OAuth2FormLoginAuthenticationFilter filter = new OAuth2FormLoginAuthenticationFilter(authenticationManager);
        filter.setUsernameParameter(uiProperties.getUsernameParameter());
        filter.setPasswordParameter(uiProperties.getPasswordParameter());
        filter.setAuthenticationDetailsSource(new OAuth2FormLoginWebAuthenticationDetailSource(uiProperties));
        filter.setAuthenticationFailureHandler(new OAuth2FormLoginAuthenticationFailureHandler(uiProperties.getFailureForwardUrl()));

        OAuth2FormLoginAuthenticationProvider provider = new OAuth2FormLoginAuthenticationProvider(captchaRendererFactory);
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);

        httpSecurity.authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
