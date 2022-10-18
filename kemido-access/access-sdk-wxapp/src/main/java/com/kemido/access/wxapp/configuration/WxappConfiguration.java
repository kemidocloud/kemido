package com.kemido.access.wxapp.configuration;

import com.kemido.access.wxapp.annotation.ConditionalOnWxappEnabled;
import com.kemido.access.wxapp.processor.WxappAccessHandler;
import com.kemido.access.wxapp.processor.WxappLogHandler;
import com.kemido.access.wxapp.processor.WxappProcessor;
import com.kemido.access.wxapp.properties.WxappProperties;
import com.kemido.assistant.core.enums.AccountType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 微信小程序后配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxappEnabled
@EnableConfigurationProperties(WxappProperties.class)
public class WxappConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WxappConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Kemido] |- SDK [Engine Access Wxapp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxappProcessor wxappProcessor(WxappProperties wxappProperties) {
        WxappProcessor wxappProcessor = new WxappProcessor();
        wxappProcessor.setWxappProperties(wxappProperties);
        wxappProcessor.setWxappLogHandler(new WxappLogHandler());
        log.trace("[Kemido] |- Bean [Wxapp Processor] Auto Configure.");
        return wxappProcessor;
    }

    @Bean(AccountType.WECHAT_MINI_APP_HANDLER)
    @ConditionalOnBean(WxappProcessor.class)
    @ConditionalOnMissingBean
    public WxappAccessHandler wxappAccessHandler(WxappProcessor wxappProcessor) {
        WxappAccessHandler wxappAccessHandler = new WxappAccessHandler(wxappProcessor);
        log.debug("[Kemido] |- Bean [Wxapp Access Handler] Auto Configure.");
        return wxappAccessHandler;
    }
}
