package com.kemido.sms.upyun.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.upyun.annotation.ConditionalOnUpyunSmsEnabled;
import com.kemido.sms.upyun.processor.UpyunSmsSendHandler;
import com.kemido.sms.upyun.properties.UpyunSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 又拍短信发送配置类 </p>
 */

@Configuration(proxyBeanMethods = false)
@ConditionalOnUpyunSmsEnabled
@EnableConfigurationProperties(UpyunSmsProperties.class)
public class UpyunSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(UpyunSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Sms Upyun] Auto Configure.");
    }

    /**
     * 构造又拍云发送处理
     *
     * @param upyunSmsProperties 配置对象
     * @return 又拍云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_UPYUN)
    public UpyunSmsSendHandler upyunSmsSendHandler(UpyunSmsProperties upyunSmsProperties) {
        UpyunSmsSendHandler upyunSmsSendHandler = new UpyunSmsSendHandler(upyunSmsProperties);
        log.debug("[Kemido] |- Bean [Upyun Sms Send Handler] Auto Configure.");
        return upyunSmsSendHandler;
    }
}
