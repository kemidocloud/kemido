package com.kemido.sms.autoconfigure;

import com.kemido.sms.all.configuration.SmsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: SMS自动注入 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        SmsConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Sms Starter] Auto Configure.");
    }
}
