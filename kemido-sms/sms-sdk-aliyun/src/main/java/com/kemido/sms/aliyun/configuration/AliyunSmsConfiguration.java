package com.kemido.sms.aliyun.configuration;

import com.kemido.sms.aliyun.annotation.ConditionalOnAliyunSmsEnabled;
import com.kemido.sms.aliyun.processor.AliyunSmsSendHandler;
import com.kemido.sms.aliyun.properties.AliyunSmsProperties;
import com.kemido.sms.core.constants.SmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 阿里云短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnAliyunSmsEnabled
@EnableConfigurationProperties(AliyunSmsProperties.class)
public class AliyunSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AliyunSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Sms Aliyun] Auto Configure.");
    }

    /**
     * 构造阿里云发送处理
     *
     * @param aliyunSmsProperties 配置对象
     * @return 阿里云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_ALIYUN)
    public AliyunSmsSendHandler aliyunSmsSendHandler(AliyunSmsProperties aliyunSmsProperties) {
        AliyunSmsSendHandler aliyunSmsSendHandler = new AliyunSmsSendHandler(aliyunSmsProperties);
        log.debug("[Kemido] |- Bean [Aliyun Sms Send Handler] Auto Configure.");
        return aliyunSmsSendHandler;
    }
}
