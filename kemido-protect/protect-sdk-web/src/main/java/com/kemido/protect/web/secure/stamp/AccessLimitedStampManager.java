package com.kemido.protect.web.secure.stamp;

import com.kemido.cache.jetcache.stamp.AbstractStampManager;
import com.kemido.protect.core.constants.ProtectConstants;
import com.kemido.protect.core.properties.SecureProperties;

/**
 * <p>Description: 防刷签章管理器 </p>
 * <p>
 * 这里使用Long类型作为值的存储类型，是为了解决该Cache 同时可以存储Duration相关的数据
 */
public class AccessLimitedStampManager extends AbstractStampManager<String, Long> {

    private final SecureProperties secureProperties;

    public AccessLimitedStampManager(SecureProperties secureProperties) {
        super(ProtectConstants.CACHE_NAME_TOKEN_ACCESS_LIMITED);
        this.secureProperties = secureProperties;
    }

    public SecureProperties getSecureProperties() {
        return secureProperties;
    }

    @Override
    public Long nextStamp(String key) {
        return 1L;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(secureProperties.getAccessLimited().getExpire());
    }
}
