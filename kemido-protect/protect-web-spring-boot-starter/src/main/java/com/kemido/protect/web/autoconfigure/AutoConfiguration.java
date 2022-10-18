package com.kemido.protect.web.autoconfigure;

import com.kemido.protect.web.configuration.HttpCryptoConfiguration;
import com.kemido.protect.web.configuration.SecureConfiguration;
import com.kemido.protect.web.configuration.TenantConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Description: Protect 模块自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        HttpCryptoConfiguration.class,
        SecureConfiguration.class,
        TenantConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Protect Web Starter] Auto Configure.");
    }
}
