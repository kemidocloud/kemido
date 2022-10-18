package com.kemido.sms.yunpian.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.yunpian.annotation.ConditionalOnYunpianSmsEnabled;
import com.kemido.sms.yunpian.processor.YunpianSmsSendHandler;
import com.kemido.sms.yunpian.properties.YunpianSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 云片网短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnYunpianSmsEnabled
@EnableConfigurationProperties(YunpianSmsProperties.class)
public class YunpianSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(YunpianSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Sms Yunpian] Auto Configure.");
    }

    /**
     * 构造云片网发送处理
     *
     * @param yunpianSmsProperties 配置对象
     * @return 云片网发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_YUNPIAN)
    public YunpianSmsSendHandler yunpianSmsSendHandler(YunpianSmsProperties yunpianSmsProperties) {
        YunpianSmsSendHandler handler = new YunpianSmsSendHandler(yunpianSmsProperties);
        log.debug("[Kemido] |- Bean [Yunpian Sms Send Handler] Auto Configure.");
        return handler;
    }
}
