package com.kemido.oauth2.server.authorization.configuration;

import com.kemido.oauth2.authorization.properties.OAuth2UiProperties;
import com.kemido.oauth2.core.properties.OAuth2ComplianceProperties;
import com.kemido.oauth2.core.properties.OAuth2Properties;
import com.kemido.oauth2.data.jpa.configuration.OAuth2DataJpaConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 * 
 * {@link org.springframework.security.oauth2.jwt.JwtTimestampValidator}
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({OAuth2Properties.class, OAuth2UiProperties.class, OAuth2ComplianceProperties.class})
@Import({OAuth2DataJpaConfiguration.class})
@EntityScan(basePackages = {
        "com.kemido.oauth2.server.authorization.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.kemido.oauth2.server.authorization.repository",
})
@ComponentScan(basePackages = {
        "com.kemido.oauth2.server.authorization.service",
        "com.kemido.oauth2.server.authorization.controller",
})
public class OAuth2AuthorizationServerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthorizationServerConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine OAuth2 Authorization Server] Auto Configure.");
    }
}
