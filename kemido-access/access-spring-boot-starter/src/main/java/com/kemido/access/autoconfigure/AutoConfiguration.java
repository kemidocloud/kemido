package com.kemido.access.autoconfigure;

import com.kemido.access.business.configuration.AccessAllConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 外部程序接入模块自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        AccessAllConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Access Starter] Auto Configure.");
    }
}
