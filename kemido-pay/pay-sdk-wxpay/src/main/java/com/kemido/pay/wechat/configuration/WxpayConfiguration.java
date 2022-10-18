package com.kemido.pay.wechat.configuration;

import com.kemido.pay.wechat.annotation.ConditionalOnWxpay;
import com.kemido.pay.wechat.definition.WxpayPaymentTemplate;
import com.kemido.pay.wechat.definition.WxpayProfileStorage;
import com.kemido.pay.wechat.properties.WxpayProperties;
import com.kemido.pay.wechat.support.WxpayDefaultProfileStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 微信支付 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxpay
@EnableConfigurationProperties(WxpayProperties.class)
public class WxpayConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WxpayConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Pay Wxpay] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxpayProfileStorage wxpayDefaultProfileStorage(WxpayProperties wxpayProperties) {
        WxpayDefaultProfileStorage wxpayDefaultProfileStorage = new WxpayDefaultProfileStorage(wxpayProperties);
        log.debug("[Kemido] |- Bean [Wxpay Default Profile Storage] Auto Configure.");
        return wxpayDefaultProfileStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxpayPaymentTemplate wxpayPaymentTemplate(WxpayProfileStorage wxpayProfileStorage, WxpayProperties wxpayProperties) {
        WxpayPaymentTemplate wxpayPaymentTemplate = new WxpayPaymentTemplate(wxpayProfileStorage, wxpayProperties);
        log.trace("[Kemido] |- Bean [Wxpay Payment Template] Auto Configure.");
        return wxpayPaymentTemplate;
    }
}
