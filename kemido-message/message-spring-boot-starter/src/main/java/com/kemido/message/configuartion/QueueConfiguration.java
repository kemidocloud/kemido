package com.kemido.message.configuartion;

import com.kemido.message.core.properties.QueueProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 消息队列配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({QueueProperties.class})
@Import({KafkaConfiguration.class})
public class QueueConfiguration {

    private static final Logger log = LoggerFactory.getLogger(QueueConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Message Queue] Auto Configure.");
    }

}
