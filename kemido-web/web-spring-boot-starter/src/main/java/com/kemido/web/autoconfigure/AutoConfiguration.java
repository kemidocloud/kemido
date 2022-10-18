package com.kemido.web.autoconfigure;

import com.kemido.web.configuration.UndertowWebServerFactoryCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Web 自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        UndertowWebServerFactoryCustomizer.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Web Starter] Auto Configure.");
    }
}
