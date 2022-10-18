package com.kemido.protect.web.configuration;

import com.kemido.protect.core.definition.AsymmetricCryptoProcessor;
import com.kemido.protect.core.definition.SymmetricCryptoProcessor;
import com.kemido.protect.web.annotation.ConditionalOnSMCrypto;
import com.kemido.protect.web.annotation.ConditionalOnStandardCrypto;
import com.kemido.protect.web.crypto.processor.AESCryptoProcessor;
import com.kemido.protect.web.crypto.processor.RSACryptoProcessor;
import com.kemido.protect.web.crypto.processor.SM2CryptoProcessor;
import com.kemido.protect.web.crypto.processor.SM4CryptoProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 非对称算法配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class CryptoStrategyConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CryptoStrategyConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Protect Crypto Strategy] Auto Configure.");
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSMCrypto
    static class SMCryptoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public AsymmetricCryptoProcessor sm2CryptoProcessor() {
            SM2CryptoProcessor sm2CryptoProcessor = new SM2CryptoProcessor();
            log.trace("[Kemido] |- Strategy [SM Asymmetric SM2 Crypto Processor] Auto Configure.");
            return sm2CryptoProcessor;
        }

        @Bean
        @ConditionalOnMissingBean
        public SymmetricCryptoProcessor sm4CryptoProcessor() {
            SM4CryptoProcessor sm4CryptoProcessor = new SM4CryptoProcessor();
            log.trace("[Kemido] |- Strategy [SM Symmetric SM4 Crypto Processor] Auto Configure.");
            return sm4CryptoProcessor;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnStandardCrypto
    static class StandardCryptoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public AsymmetricCryptoProcessor rsaCryptoProcessor() {
            RSACryptoProcessor rsaCryptoProcessor = new RSACryptoProcessor();
            log.trace("[Kemido] |- Strategy [Standard Asymmetric RSA Crypto Processor] Auto Configure.");
            return rsaCryptoProcessor;
        }

        @Bean
        @ConditionalOnMissingBean
        public SymmetricCryptoProcessor aesCryptoProcessor() {
            AESCryptoProcessor aesCryptoProcessor = new AESCryptoProcessor();
            log.trace("[Kemido] |- Strategy [Standard Symmetric AES Crypto Processor] Auto Configure.");
            return aesCryptoProcessor;
        }
    }
}
