package com.kemido.assistant.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Description: 运行环境信息相关工具类 </p>
 */
public class EnvUtils {

    private static final Logger log = LoggerFactory.getLogger(EnvUtils.class);

    /**
     * 获取运行主机ip地址
     *
     * @return ip地址，或者null
     */
    public static String getHostAddress() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("[Kemido] |- Get host address error: {}", e.getLocalizedMessage());
            return null;
        }
    }
}
