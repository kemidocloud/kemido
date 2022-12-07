package com.kemido.sms.chinamobile.configuration;

import com.kemido.sms.chinamobile.annotation.ConditionalOnChinaMobileSmsEnabled;
import com.kemido.sms.chinamobile.processor.ChinaMobileSmsSendHandler;
import com.kemido.sms.chinamobile.properties.ChinaMobileSmsProperties;
import com.kemido.sms.core.constants.SmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 移动云短信发送配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnChinaMobileSmsEnabled
@EnableConfigurationProperties(ChinaMobileSmsProperties.class)
public class ChinaMobileSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ChinaMobileSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Sms China Mobile] Auto Configure.");
    }

    /**
     * 构造移动云发送处理
     *
     * @param chinaMobileSmsProperties 配置对象
     * @return 移动云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_CHINA_MOBILE)
    public ChinaMobileSmsSendHandler chinaMobileSmsSendHandler(ChinaMobileSmsProperties chinaMobileSmsProperties) {
        ChinaMobileSmsSendHandler chinaMobileSmsSendHandler = new ChinaMobileSmsSendHandler(chinaMobileSmsProperties);
        log.debug("[Kemido] |- Bean [China Mobile Sms Send Handler] Auto Configure.");
        return chinaMobileSmsSendHandler;
    }
}
