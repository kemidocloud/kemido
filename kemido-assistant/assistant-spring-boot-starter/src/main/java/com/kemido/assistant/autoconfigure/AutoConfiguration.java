package com.kemido.assistant.autoconfigure;

import cn.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Definition 自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        SpringUtil.class,
        JacksonConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Assistant Starter] Auto Configure.");
    }
}
