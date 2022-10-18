package com.kemido.message.autoconfigure;

import com.kemido.message.configuartion.QueueConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Message 模块自动注入配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({QueueConfiguration.class})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(QueueConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Message Starter] Auto Configure.");
    }
}
