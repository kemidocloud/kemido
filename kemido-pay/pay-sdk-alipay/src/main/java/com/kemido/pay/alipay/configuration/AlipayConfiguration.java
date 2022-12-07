package com.kemido.pay.alipay.configuration;

import com.kemido.pay.alipay.annotation.ConditionalOnAlipayEnabled;
import com.kemido.pay.alipay.definition.AlipayPaymentTemplate;
import com.kemido.pay.alipay.definition.AlipayProfileStorage;
import com.kemido.pay.alipay.properties.AlipayProperties;
import com.kemido.pay.alipay.support.AlipayDefaultProfileStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 支付宝配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnAlipayEnabled
@EnableConfigurationProperties(AlipayProperties.class)
@ComponentScan(basePackages = {
        "com.kemido.pay.alipay.controller"
})

public class AlipayConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AlipayConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Pay Alipay] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public AlipayProfileStorage alipayProfileDefaultStorage(AlipayProperties alipayProperties) {
        AlipayDefaultProfileStorage alipayDefaultProfileStorage = new AlipayDefaultProfileStorage(alipayProperties);
        log.debug("[Kemido] |- Bean [Alipay Profile Default Storage] Auto Configure.");
        return alipayDefaultProfileStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public AlipayPaymentTemplate alipayPaymentTemplate(AlipayProfileStorage alipayProfileStorage, AlipayProperties alipayProperties) {
        AlipayPaymentTemplate alipayPaymentTemplate = new AlipayPaymentTemplate(alipayProfileStorage, alipayProperties);
        log.trace("[Kemido] |- Bean [Alipay Payment Template] Auto Configure.");
        return alipayPaymentTemplate;
    }

}
