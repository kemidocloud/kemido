package com.kemido.sms.tencent.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.tencent.annotation.ConditionalOnTencentSmsEnabled;
import com.kemido.sms.tencent.processor.TencentSmsSendHandler;
import com.kemido.sms.tencent.properties.TencentSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 腾讯云短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnTencentSmsEnabled
@EnableConfigurationProperties(TencentSmsProperties.class)
public class TencentSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TencentSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Sms Tencent] Auto Configure.");
    }

    /**
     * 构造腾讯云V3发送处理
     *
     * @param tencentSmsProperties 配置对象
     * @return 腾讯云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_TENCENT_CLOUD)
    public TencentSmsSendHandler tencentCloudSmsSendHandler(TencentSmsProperties tencentSmsProperties) {
        TencentSmsSendHandler tencentSmsSendHandler = new TencentSmsSendHandler(tencentSmsProperties);
        log.debug("[Kemido] |- Bean [Tencent Sms Send Handler] Auto Configure.");
        return tencentSmsSendHandler;
    }
}
