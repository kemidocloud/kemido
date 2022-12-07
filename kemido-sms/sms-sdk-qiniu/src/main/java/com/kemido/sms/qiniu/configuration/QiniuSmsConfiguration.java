package com.kemido.sms.qiniu.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.qiniu.annotation.ConditionalOnQiniuSmsEnabled;
import com.kemido.sms.qiniu.processor.QiniuSmsSendHandler;
import com.kemido.sms.qiniu.properties.QiniuSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 七牛云短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnQiniuSmsEnabled
@EnableConfigurationProperties(QiniuSmsProperties.class)
public class QiniuSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(QiniuSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Sms Qiniu] Auto Configure.");
    }

    /**
     * 构造七牛云发送处理
     *
     * @param qiniuSmsProperties 配置对象
     * @return 七牛云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_QINIU)
    public QiniuSmsSendHandler qiniuSmsSendHandler(QiniuSmsProperties qiniuSmsProperties) {
        QiniuSmsSendHandler qiniuSmsSendHandler = new QiniuSmsSendHandler(qiniuSmsProperties);
        log.debug("[Kemido] |- Bean [Qiniu Sms Send Handler] Auto Configure.");
        return qiniuSmsSendHandler;
    }
}
