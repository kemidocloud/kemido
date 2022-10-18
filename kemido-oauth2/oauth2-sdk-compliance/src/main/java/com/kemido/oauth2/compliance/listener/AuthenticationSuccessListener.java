package com.kemido.oauth2.compliance.listener;

import com.kemido.oauth2.authorization.domain.UserAuthenticationDetails;
import com.kemido.oauth2.compliance.service.OAuth2ComplianceService;
import com.kemido.oauth2.compliance.stamp.SignInFailureLimitedStampManager;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: 登录成功事件监听 </p>
 */
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessListener.class);

    private final SignInFailureLimitedStampManager stampManager;
    private final OAuth2ComplianceService complianceService;

    public AuthenticationSuccessListener(SignInFailureLimitedStampManager stampManager, OAuth2ComplianceService complianceService) {
        this.stampManager = stampManager;
        this.complianceService = complianceService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        log.debug("[Kemido] |- Authentication Success Listener!");

        Authentication authentication = event.getAuthentication();

        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            OAuth2AccessTokenAuthenticationToken authenticationToken = (OAuth2AccessTokenAuthenticationToken) authentication;
            Object details = authentication.getDetails();

            String username = null;
            if (ObjectUtils.isNotEmpty(details) && details instanceof UserAuthenticationDetails) {
                UserAuthenticationDetails user = (UserAuthenticationDetails) details;
                username = user.getUserName();
            }

            String clientId = authenticationToken.getRegisteredClient().getId();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (ObjectUtils.isNotEmpty(request) && StringUtils.isNotBlank(username)) {
                complianceService.save(username, clientId, "用户登录", request);
                String key = SecureUtil.md5(username);
                boolean hasKey = stampManager.containKey(key);
                if (hasKey) {
                    stampManager.delete(key);
                }
            } else {
                log.warn("[Kemido] |- Can not get request and username, skip!");
            }
        }
    }
}
