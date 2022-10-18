package com.kemido.oauth2.compliance.service;

import com.kemido.data.core.enums.DataItemStatus;
import com.kemido.oauth2.compliance.definition.AccountStatusChangeService;
import com.kemido.oauth2.compliance.stamp.LockedUserDetailsStampManager;
import com.kemido.oauth2.core.definition.domain.KemidoUser;
import com.kemido.oauth2.core.definition.service.EnhanceUserDetailsService;
import com.kemido.web.core.domain.UserStatus;
import jodd.util.StringUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 账户锁定处理服务 </p>
 */
@Service
public class OAuth2AccountStatusService {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccountStatusService.class);

    private final UserDetailsService userDetailsService;
    private final AccountStatusChangeService accountStatusChangeService;
    private final LockedUserDetailsStampManager userDetailsStampManager;

    @Autowired
    public OAuth2AccountStatusService(UserDetailsService userDetailsService, AccountStatusChangeService accountStatusChangeService, LockedUserDetailsStampManager userDetailsStampManager) {
        this.userDetailsService = userDetailsService;
        this.userDetailsStampManager = userDetailsStampManager;
        this.accountStatusChangeService = accountStatusChangeService;
    }

    private EnhanceUserDetailsService getUserDetailsService() {
        return (EnhanceUserDetailsService) userDetailsService;
    }

    private String getUserId(String username) {
        EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
        KemidoUser user = enhanceUserDetailsService.loadKemidoUserByUsername(username);
        if (ObjectUtils.isNotEmpty(user)) {
            return user.getUserId();
        }

        log.warn("[Kemido] |- Can not found the userid for [{}]", username);
        return null;
    }

    public void lock(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusChangeService.process(new UserStatus(userId, DataItemStatus.LOCKING.name()));
            userDetailsStampManager.put(userId, username);
            log.info("[Kemido] |- User count [{}] has been locked, and record into cache!", username);
        }
    }

    public void enable(String userId) {
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusChangeService.process(new UserStatus(userId, DataItemStatus.ENABLE.name()));
        }
    }

    public void releaseFromCache(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            String value = userDetailsStampManager.get(userId);
            if (StringUtil.isNotEmpty(value)) {
                this.userDetailsStampManager.delete(userId);
                log.info("[Kemido] |- User count [{}] locked info has been release!", username);
            }
        }
    }
}
