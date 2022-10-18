package com.kemido.assistant.core.support;

import com.kemido.assistant.core.constants.BaseConstants;
import org.springframework.core.env.Environment;

/**
 * <p>Description: 通用属性读取器 </p>
 */
public class PropertyFinder {

    public static String getApplicationName(Environment environment) {
        return PropertyResolver.getProperty(environment, BaseConstants.ITEM_SPRING_APPLICATION_NAME);
    }

    public static String getServerPort(Environment environment) {
        return PropertyResolver.getProperty(environment, BaseConstants.ITEM_SERVER_PORT);
    }

    public static String getSessionStoreType(Environment environment) {
        return PropertyResolver.getProperty(environment, BaseConstants.ITEM_SPRING_SESSION_STORE_TYPE);
    }
}
