package com.kemido.oauth2.compliance.configuration;

import com.kemido.oauth2.compliance.definition.AccountStatusChangeService;
import com.kemido.oauth2.compliance.listener.AccountStatusListener;
import com.kemido.oauth2.compliance.listener.AuthenticationFailureListener;
import com.kemido.oauth2.compliance.listener.AuthenticationSuccessListener;
import com.kemido.oauth2.compliance.service.OAuth2AccountStatusService;
import com.kemido.oauth2.compliance.service.OAuth2ComplianceService;
import com.kemido.oauth2.compliance.stamp.SignInFailureLimitedStampManager;
import com.kemido.oauth2.core.annotation.ConditionalOnAutoUnlockUserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.PostConstruct;

/**
 * <p>Description: OAuth2 应用安全合规配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(AccountStatusChangeService.class)
@EntityScan(basePackages = {
        "com.kemido.oauth2.compliance.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.kemido.oauth2.compliance.repository",
})
@ComponentScan(basePackages = {
        "com.kemido.oauth2.compliance.stamp",
        "com.kemido.oauth2.compliance.service",
        "com.kemido.oauth2.compliance.controller",
})
public class OAuth2ComplianceConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ComplianceConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [OAuth2 Compliance] Auto Configure.");
    }

    @Bean
    @ConditionalOnAutoUnlockUserAccount
    public AccountStatusListener accountLockStatusListener(RedisMessageListenerContainer redisMessageListenerContainer, OAuth2AccountStatusService accountLockService) {
        AccountStatusListener lockStatusListener = new AccountStatusListener(redisMessageListenerContainer, accountLockService);
        log.trace("[Kemido] |- Bean [OAuth2 Account Lock Status Listener] Auto Configure.");
        return lockStatusListener;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFailureListener authenticationFailureListener(SignInFailureLimitedStampManager stampManager, OAuth2AccountStatusService accountLockService) {
        AuthenticationFailureListener authenticationFailureListener = new AuthenticationFailureListener(stampManager, accountLockService);
        log.trace("[Kemido] |- Bean [OAuth2 Authentication Failure Listener] Auto Configure.");
        return authenticationFailureListener;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationSuccessListener authenticationSuccessListener(SignInFailureLimitedStampManager stampManager, OAuth2ComplianceService complianceService) {
        AuthenticationSuccessListener authenticationSuccessListener = new AuthenticationSuccessListener(stampManager, complianceService);
        log.trace("[Kemido] |- Bean [OAuth2 Authentication Success Listener] Auto Configure.");
        return authenticationSuccessListener;
    }

}
