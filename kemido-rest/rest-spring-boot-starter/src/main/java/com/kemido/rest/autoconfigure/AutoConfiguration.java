package com.kemido.rest.autoconfigure;

import com.kemido.rest.configuration.RestCryptoConfiguration;
import com.kemido.rest.configuration.RestWebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Rest 自动注入 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        RestWebConfiguration.class,
        RestCryptoConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Rest Starter] Auto Configure.");
    }
}
