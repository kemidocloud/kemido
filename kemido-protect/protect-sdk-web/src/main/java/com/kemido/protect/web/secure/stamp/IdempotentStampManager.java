package com.kemido.protect.web.secure.stamp;

import com.kemido.cache.jetcache.stamp.AbstractStampManager;
import com.kemido.protect.core.constants.ProtectConstants;
import com.kemido.protect.core.properties.SecureProperties;
import cn.hutool.core.util.IdUtil;

/**
 * <p>Description: 幂等Stamp管理 </p>
 */
public class IdempotentStampManager extends AbstractStampManager<String, String> {

    private final SecureProperties secureProperties;

    public IdempotentStampManager(SecureProperties secureProperties) {
        super(ProtectConstants.CACHE_NAME_TOKEN_IDEMPOTENT);
        this.secureProperties = secureProperties;
    }

    public SecureProperties getSecureProperties() {
        return secureProperties;
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(secureProperties.getIdempotent().getExpire());
    }
}
