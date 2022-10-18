package com.kemido.access.wxmpp.configuration;

import com.kemido.access.wxmpp.annotation.ConditionalOnWxmppEnabled;
import com.kemido.access.wxmpp.processor.WxmppLogHandler;
import com.kemido.access.wxmpp.processor.WxmppProcessor;
import com.kemido.access.wxmpp.properties.WxmppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 微信公众号配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxmppEnabled
@EnableConfigurationProperties(WxmppProperties.class)
public class WxmppConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WxmppConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Kemido] |- SDK [Engine Access Wxmpp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxmppProcessor wxmppProcessor(WxmppProperties wxmppProperties, StringRedisTemplate stringRedisTemplate) {
        WxmppProcessor wxmppProcessor = new WxmppProcessor();
        wxmppProcessor.setWxmppProperties(wxmppProperties);
        wxmppProcessor.setWxmppLogHandler(new WxmppLogHandler());
        wxmppProcessor.setStringRedisTemplate(stringRedisTemplate);
        log.trace("[Kemido] |- Bean [Wxmpp Processor] Auto Configure.");
        return wxmppProcessor;
    }
}
