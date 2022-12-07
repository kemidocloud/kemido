package com.kemido.access.business.configuration;

import com.kemido.access.business.controller.JustAuthAccessController;
import com.kemido.access.business.controller.PhoneNumberAccessController;
import com.kemido.access.business.controller.WxappAccessController;
import com.kemido.access.business.processor.AccessHandlerStrategyFactory;
import com.kemido.access.business.processor.PhoneNumberAccessHandler;
import com.kemido.access.justauth.annotation.ConditionalOnJustAuthEnabled;
import com.kemido.access.justauth.configuration.JustAuthConfiguration;
import com.kemido.access.wxapp.annotation.ConditionalOnWxappEnabled;
import com.kemido.access.wxapp.configuration.WxappConfiguration;
import com.kemido.access.wxmpp.configuration.WxmppConfiguration;
import com.kemido.assistant.core.enums.AccountType;
import com.kemido.sms.all.annotation.ConditionalOnSmsEnabled;
import com.kemido.sms.all.configuration.SmsConfiguration;
import com.kemido.sms.all.processor.SmsSendStrategyFactory;
import com.kemido.sms.all.stamp.VerificationCodeStampManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Access 业务模块配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        JustAuthConfiguration.class,
        WxappConfiguration.class,
        WxmppConfiguration.class
})
public class AccessAllConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessAllConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Kemido] |- SDK [Access All] Auto Configure.");
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSmsEnabled
    @Import({SmsConfiguration.class})
    static class PhoneNumberSignInConfiguration {

        @Bean(AccountType.PHONE_NUMBER_HANDLER)
        @ConditionalOnBean({VerificationCodeStampManager.class, SmsSendStrategyFactory.class})
        public PhoneNumberAccessHandler phoneNumberAccessHandler(VerificationCodeStampManager verificationCodeStampManager, SmsSendStrategyFactory smsSendStrategyFactory) {
            PhoneNumberAccessHandler phoneNumberAuthenticationHandler = new PhoneNumberAccessHandler(verificationCodeStampManager, smsSendStrategyFactory);
            log.trace("[Kemido] |- Bean [Phone Number SignIn Handler] Auto Configure.");
            return phoneNumberAuthenticationHandler;
        }
    }

    @Bean
    @ConditionalOnMissingBean(AccessHandlerStrategyFactory.class)
    public AccessHandlerStrategyFactory accessHandlerStrategyFactory() {
        AccessHandlerStrategyFactory accessHandlerStrategyFactory = new AccessHandlerStrategyFactory();
        log.trace("[Kemido] |- Bean [Access Handler Strategy Factory] Auto Configure.");
        return accessHandlerStrategyFactory;
    }

    @Configuration(proxyBeanMethods = false)
    static class ControllerConfiguration {

        @PostConstruct
        public void init() {
            log.debug("[Kemido] |- SDK [Access All Controller] Auto Configure.");
        }

        @Bean
        @ConditionalOnSmsEnabled
        @ConditionalOnMissingBean
        public PhoneNumberAccessController phoneNumberAccessController() {
            PhoneNumberAccessController phoneNumberAuthenticationController = new PhoneNumberAccessController();
            log.trace("[Kemido] |- Bean [Phone Number Access Controller] Auto Configure.");
            return phoneNumberAuthenticationController;
        }

        @Bean
        @ConditionalOnJustAuthEnabled
        @ConditionalOnMissingBean
        public JustAuthAccessController justAuthSignInController() {
            JustAuthAccessController justAuthAuthenticationController = new JustAuthAccessController();
            log.trace("[Kemido] |- Bean [Just Auth Access Controller] Auto Configure.");
            return justAuthAuthenticationController;
        }

        @Bean
        @ConditionalOnWxappEnabled
        @ConditionalOnMissingBean
        public WxappAccessController wxappAccessController() {
            WxappAccessController wxappAccessController = new WxappAccessController();
            log.trace("[Kemido] |- Bean [Wxapp Access Controller] Auto Configure.");
            return wxappAccessController;
        }
    }
}
