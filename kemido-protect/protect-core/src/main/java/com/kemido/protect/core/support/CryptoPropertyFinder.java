package com.kemido.protect.core.support;


import com.kemido.assistant.core.support.PropertyFinder;
import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.protect.core.constants.ProtectConstants;
import org.springframework.core.env.Environment;

/**
 * <p>Description: 策略模块配置获取器 </p>
 */
public class CryptoPropertyFinder extends PropertyFinder {

    public static String getCryptoStrategy(Environment environment, String defaultValue) {
        return PropertyResolver.getProperty(environment, ProtectConstants.ITEM_PROTECT_CRYPTO_STRATEGY, defaultValue);
    }

    public static String getCryptoStrategy(Environment environment) {
        return PropertyResolver.getProperty(environment, ProtectConstants.ITEM_PROTECT_CRYPTO_STRATEGY);
    }
}
