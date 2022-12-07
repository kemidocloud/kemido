package com.kemido.sms.jd.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.jd.annotation.ConditionalOnJdSmsEnabled;
import com.kemido.sms.jd.processor.JdSmsSendHandler;
import com.kemido.sms.jd.properties.JdSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 京东云短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnJdSmsEnabled
@EnableConfigurationProperties(JdSmsProperties.class)
public class JdSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JdSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Sms Jd] Auto Configure.");
    }

    /**
     * 构造京东云发送处理
     *
     * @param jdSmsProperties 配置对象
     * @return 京东云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_JD_CLOUD)
    public JdSmsSendHandler jdCloudSmsSendHandler(JdSmsProperties jdSmsProperties) {
        JdSmsSendHandler jdSmsSendHandler = new JdSmsSendHandler(jdSmsProperties);
        log.debug("[Kemido] |- Bean [Jd Sms Send Handler] Auto Configure.");
        return jdSmsSendHandler;
    }
}
