package com.kemido.pay.all.configuration;

import com.kemido.pay.alipay.configuration.AlipayConfiguration;
import com.kemido.pay.wechat.configuration.WxpayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 支付配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({AlipayConfiguration.class, WxpayConfiguration.class})
public class PayConfiguration {

    private static final Logger log = LoggerFactory.getLogger(PayConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Pay All] Auto Configure.");
    }
}
