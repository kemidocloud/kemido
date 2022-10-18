package com.kemido.websocket.autoconfigure;

import com.kemido.websocket.accelerator.configuration.WebSocketConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Web Socket 自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        WebSocketConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine WebSocket Starter] Auto Configure.");
    }
}
