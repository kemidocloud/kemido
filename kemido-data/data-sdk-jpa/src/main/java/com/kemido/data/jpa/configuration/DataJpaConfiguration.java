package com.kemido.data.jpa.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Data JPA 模块可配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        MultiTenancyConfiguration.class
})
public class DataJpaConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataJpaConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- Module [Engine Data JPA] Auto Configure.");
    }
}
