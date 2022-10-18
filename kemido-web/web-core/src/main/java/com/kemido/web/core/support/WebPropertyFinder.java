package com.kemido.web.core.support;

import com.kemido.assistant.core.support.PropertyFinder;
import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.web.core.constants.WebConstants;
import org.springframework.core.env.Environment;

/**
 * <p>Description: Web属性处理器 </p>
 */
public class WebPropertyFinder extends PropertyFinder {

    public static boolean isScanEnabled(Environment environment) {
        return PropertyResolver.getBoolean(environment, WebConstants.ITEM_SCAN_ENABLED);
    }

    public static boolean isFeignOkHttpEnabled(Environment environment) {
        return PropertyResolver.getBoolean(environment, WebConstants.ITEM_FEIGN_OKHTTP_ENABLED);
    }

    public static boolean isFeignHttpClientEnabled(Environment environment) {
        return PropertyResolver.getBoolean(environment, WebConstants.ITEM_FEIGN_HTTPCLIENT_ENABLED);
    }
}
