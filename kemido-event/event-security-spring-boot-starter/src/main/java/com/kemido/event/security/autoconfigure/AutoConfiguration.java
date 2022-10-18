package com.kemido.event.security.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Security Event 自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@RemoteApplicationEventScan({
        "com.kemido.event.security.remote"
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Event Security Starter] Auto Configure.");
    }
}
