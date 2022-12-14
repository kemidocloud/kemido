package com.kemido.event.pay.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Pay Event 自动注入配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@RemoteApplicationEventScan({
        "com.kemido.event.pay.remote"
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Event Pay Starter] Auto Configure.");
    }
}
