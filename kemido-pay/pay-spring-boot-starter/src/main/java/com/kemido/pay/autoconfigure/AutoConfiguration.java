package com.kemido.pay.autoconfigure;

import com.kemido.pay.all.configuration.PayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Starter 自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        PayConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Pay Starter] Auto Configure.");
    }
}
