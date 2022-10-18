package com.kemido.oauth2.metadata.configuration;

import com.kemido.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import com.kemido.oauth2.metadata.listener.RemoteSecurityMetadataSyncListener;
import com.kemido.oauth2.metadata.processor.ExpressionSecurityMetadataParser;
import com.kemido.oauth2.metadata.processor.SecurityMetadataAnalysisProcessor;
import com.kemido.oauth2.metadata.storage.SecurityMetadataLocalStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: SecurityMetadata 配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityMetadataConfiguration extends GlobalMethodSecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityMetadataConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- SDK [Rocket Security Metadata] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMetadataLocalStorage securityMetadataLocalStorage() {
        SecurityMetadataLocalStorage securityMetadataLocalStorage = new SecurityMetadataLocalStorage();
        log.trace("[Kemido] |- Bean [Security Metadata Local Storage] Auto Configure.");
        return securityMetadataLocalStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public ExpressionSecurityMetadataParser securityMetadataExpressionParser() {
        ExpressionSecurityMetadataParser expressionSecurityMetadataParser = new ExpressionSecurityMetadataParser(this.getExpressionHandler().getExpressionParser());
        log.trace("[Kemido] |- Bean [Security Metadata Expression Parser] Auto Configure.");
        return expressionSecurityMetadataParser;
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMetadataAnalysisProcessor securityMetadataProcessService(SecurityMetadataLocalStorage securityMetadataLocalStorage, ExpressionSecurityMetadataParser expressionSecurityMetadataParser) {
        SecurityMetadataAnalysisProcessor securityMetadataProcessService = new SecurityMetadataAnalysisProcessor(securityMetadataLocalStorage, expressionSecurityMetadataParser);
        log.trace("[Kemido] |- Bean [Security Metadata Process Service] Auto Configure.");
        return securityMetadataProcessService;
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener(SecurityMetadataAnalysisProcessor securityMetadataProcessService, ServiceMatcher serviceMatcher) {
        RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener = new RemoteSecurityMetadataSyncListener(securityMetadataProcessService, serviceMatcher);
        log.trace("[Kemido] |- Bean [Security Metadata Refresh Listener] Auto Configure.");
        return remoteSecurityMetadataSyncListener;
    }
}
