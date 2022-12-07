package com.kemido.sms.all.configuration;

import com.kemido.sms.aliyun.configuration.AliyunSmsConfiguration;
import com.kemido.sms.all.annotation.ConditionalOnSmsEnabled;
import com.kemido.sms.all.processor.SmsSendStrategyFactory;
import com.kemido.sms.all.properties.SmsProperties;
import com.kemido.sms.all.stamp.VerificationCodeStampManager;
import com.kemido.sms.chinamobile.configuration.ChinaMobileSmsConfiguration;
import com.kemido.sms.core.definition.SmsSendHandler;
import com.kemido.sms.huawei.configuration.HuaweiSmsConfiguration;
import com.kemido.sms.jd.configuration.JdSmsConfiguration;
import com.kemido.sms.netease.configuration.NeteaseSmsConfiguration;
import com.kemido.sms.qiniu.configuration.QiniuSmsConfiguration;
import com.kemido.sms.tencent.configuration.TencentSmsConfiguration;
import com.kemido.sms.upyun.configuration.UpyunSmsConfiguration;
import com.kemido.sms.yunpian.configuration.YunpianSmsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 发送短信统一配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnSmsEnabled
@EnableConfigurationProperties({SmsProperties.class})
@Import({
        AliyunSmsConfiguration.class,
        ChinaMobileSmsConfiguration.class,
        HuaweiSmsConfiguration.class,
        JdSmsConfiguration.class,
        NeteaseSmsConfiguration.class,
        QiniuSmsConfiguration.class,
        TencentSmsConfiguration.class,
        UpyunSmsConfiguration.class,
        YunpianSmsConfiguration.class,
})
public class SmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Sms All] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public VerificationCodeStampManager verificationCodeStampManager(SmsProperties smsProperties) {
        VerificationCodeStampManager verificationCodeStampManager = new VerificationCodeStampManager();
        verificationCodeStampManager.setSmsProperties(smsProperties);
        log.trace("[Kemido] |- Bean [Verification Code Stamp Manager] Auto Configure.");
        return verificationCodeStampManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(SmsSendHandler.class)
    public SmsSendStrategyFactory smsSendStrategyFactory(SmsProperties smsProperties) {
        SmsSendStrategyFactory smsSendStrategyFactory = new SmsSendStrategyFactory();
        smsSendStrategyFactory.setSmsProperties(smsProperties);
        log.trace("[Kemido] |- Bean [Sms Send Strategy Factory] Auto Configure.");
        return smsSendStrategyFactory;
    }
}
