package com.kemido.oauth2.compliance.listener;

import com.kemido.cache.core.exception.MaximumLimitExceededException;
import com.kemido.oauth2.compliance.service.OAuth2AccountStatusService;
import com.kemido.oauth2.compliance.stamp.SignInFailureLimitedStampManager;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.time.Duration;
import java.util.Map;

/**
 * <p>Description: 登出成功监听 </p>
 */
public class AuthenticationFailureListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFailureListener.class);

    private final SignInFailureLimitedStampManager stampManager;
    private final OAuth2AccountStatusService accountLockService;

    public AuthenticationFailureListener(SignInFailureLimitedStampManager stampManager, OAuth2AccountStatusService accountLockService) {
        this.stampManager = stampManager;
        this.accountLockService = accountLockService;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {

        log.debug("[Kemido] |- User sign in catch failure event : [{}].", event.getClass().getName());

        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            Authentication authentication = event.getAuthentication();

            String username = null;

            if (authentication instanceof OAuth2AuthorizationGrantAuthenticationToken) {

                log.debug("[Kemido] |- Toke object in failure event  is OAuth2AuthorizationGrantAuthenticationToken");

                OAuth2AuthorizationGrantAuthenticationToken token = (OAuth2AuthorizationGrantAuthenticationToken) authentication;
                Map<String, Object> params = token.getAdditionalParameters();
                username = getPrincipal(params);
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {

                log.debug("[Kemido] |- Toke object in failure event  is UsernamePasswordAuthenticationToken");

                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
                Object principal =  token.getPrincipal();
                if (principal instanceof String) {
                    username = (String) principal;
                }
            }

            if (StringUtils.isNotBlank(username)) {

                log.debug("[Kemido] |- Parse the username in failure event is [{}].", username);

                int maxTimes = stampManager.getComplianceProperties().getSignInFailureLimited().getMaxTimes();
                Duration expire = stampManager.getComplianceProperties().getSignInFailureLimited().getExpire();
                try {
                    int times = stampManager.counting(username, maxTimes, expire, true, "AuthenticationFailureListener");
                    log.debug("[Kemido] |- Sign in user input password error [{}] items", times);
                }catch (MaximumLimitExceededException e) {
                    log.warn("[Kemido] |- User [{}] password error [{}] items, LOCK ACCOUNT!", username, maxTimes);
                    accountLockService.lock(username);
                }
            }
        }
    }

    private String getPrincipal(Map<String, Object> params) {
        if (MapUtils.isNotEmpty(params)) {
            if (params.containsKey(OAuth2ParameterNames.USERNAME)) {
                Object value = params.get(OAuth2ParameterNames.USERNAME);
                if (ObjectUtils.isNotEmpty(value)) {
                    return (String) value;
                }
            }
        }

        return null;
    }
}
