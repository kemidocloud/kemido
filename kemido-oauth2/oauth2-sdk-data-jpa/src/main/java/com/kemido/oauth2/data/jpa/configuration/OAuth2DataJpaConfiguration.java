package com.kemido.oauth2.data.jpa.configuration;

import com.kemido.oauth2.data.jpa.service.KemidoAuthorizationConsentService;
import com.kemido.oauth2.data.jpa.service.KemidoAuthorizationService;
import com.kemido.oauth2.data.jpa.service.KemidoRegisteredClientService;
import com.kemido.oauth2.data.jpa.storage.JpaOAuth2AuthorizationConsentService;
import com.kemido.oauth2.data.jpa.storage.JpaOAuth2AuthorizationService;
import com.kemido.oauth2.data.jpa.storage.JpaRegisteredClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import javax.annotation.PostConstruct;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
        "com.kemido.oauth2.data.jpa.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.kemido.oauth2.data.jpa.repository",
})
@ComponentScan(basePackages = {
        "com.kemido.oauth2.data.jpa.service",
})
public class OAuth2DataJpaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2DataJpaConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine OAuth2 Data JPA] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RegisteredClientRepository registeredClientRepository(KemidoRegisteredClientService kemidoRegisteredClientService, PasswordEncoder passwordEncoder) {
        JpaRegisteredClientRepository jpaRegisteredClientRepository = new JpaRegisteredClientRepository(kemidoRegisteredClientService, passwordEncoder);
        log.debug("[Kemido] |- Bean [Jpa Registered Client Repository] Auto Configure.");
        return jpaRegisteredClientRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationService authorizationService(KemidoAuthorizationService kemidoAuthorizationService, RegisteredClientRepository registeredClientRepository) {
        JpaOAuth2AuthorizationService jpaOAuth2AuthorizationService = new JpaOAuth2AuthorizationService(kemidoAuthorizationService, registeredClientRepository);
        log.debug("[Kemido] |- Bean [Jpa OAuth2 Authorization Service] Auto Configure.");
        return jpaOAuth2AuthorizationService;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationConsentService authorizationConsentService(KemidoAuthorizationConsentService kemidoAuthorizationConsentService, RegisteredClientRepository registeredClientRepository) {
        JpaOAuth2AuthorizationConsentService jpaOAuth2AuthorizationConsentService = new JpaOAuth2AuthorizationConsentService(kemidoAuthorizationConsentService, registeredClientRepository);
        log.debug("[Kemido] |- Bean [Jpa OAuth2 Authorization Consent Service] Auto Configure.");
        return jpaOAuth2AuthorizationConsentService;
    }
}
