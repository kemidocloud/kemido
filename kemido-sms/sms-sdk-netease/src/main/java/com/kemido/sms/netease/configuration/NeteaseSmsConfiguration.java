package com.kemido.sms.netease.configuration;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.netease.annotation.ConditionalOnNeteaseSmsEnabled;
import com.kemido.sms.netease.processor.NeteaseSmsSendHandler;
import com.kemido.sms.netease.properties.NeteaseSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 网易短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnNeteaseSmsEnabled
@EnableConfigurationProperties(NeteaseSmsProperties.class)
public class NeteaseSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(NeteaseSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Sms Netease] Auto Configure.");
    }

    /**
     * 构造网易云信发送处理
     *
     * @param neteaseSmsProperties 配置对象
     * @return 网易云信发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_NETEASE_CLOUD)
    public NeteaseSmsSendHandler neteaseCloudSmsSendHandler(NeteaseSmsProperties neteaseSmsProperties) {
        NeteaseSmsSendHandler neteaseSmsSendHandler = new NeteaseSmsSendHandler(neteaseSmsProperties);
        log.debug("[Kemido] |- Bean [Netease Sms Send Handler] Auto Configure.");
        return neteaseSmsSendHandler;
    }
}
