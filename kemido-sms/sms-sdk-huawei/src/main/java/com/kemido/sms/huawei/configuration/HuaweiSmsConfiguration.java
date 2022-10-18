package com.kemido.sms.huawei.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.huawei.annotation.ConditionalOnHuaweiSmsEnabled;
import com.kemido.sms.huawei.processor.HuaweiSmsSendHandler;
import com.kemido.sms.huawei.properties.HuaweiSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 华为云短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnHuaweiSmsEnabled
@EnableConfigurationProperties(HuaweiSmsProperties.class)
public class HuaweiSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HuaweiSmsConfiguration.class);

    /**
     * 构造华为云发送处理
     *
     * @param huaweiSmsProperties 配置对象
     * @return 华为云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_HUAWEI_CLOUD)
    public HuaweiSmsSendHandler huaweiCloudSmsSendHandler(HuaweiSmsProperties huaweiSmsProperties) {
        HuaweiSmsSendHandler huaweiSmsSendHandler = new HuaweiSmsSendHandler(huaweiSmsProperties);
        log.debug("[Kemido] |- Bean [Huawei Sms Send Handler] Auto Configure.");
        return huaweiSmsSendHandler;
    }
}
