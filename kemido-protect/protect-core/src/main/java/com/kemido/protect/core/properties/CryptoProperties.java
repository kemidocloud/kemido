package com.kemido.protect.core.properties;

import com.kemido.protect.core.constants.ProtectConstants;
import com.kemido.protect.core.enums.CryptoStrategy;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 加密算法配置 </p>
 */
@ConfigurationProperties(prefix = ProtectConstants.PROPERTY_PROTECT_CRYPTO)
public class CryptoProperties {

    /**
     * 加密算法策略，默认：国密算法
     */
    private CryptoStrategy cryptoStrategy = CryptoStrategy.SM;

    public CryptoStrategy getCryptoStrategy() {
        return cryptoStrategy;
    }

    public void setCryptoStrategy(CryptoStrategy cryptoStrategy) {
        this.cryptoStrategy = cryptoStrategy;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("strategy", cryptoStrategy)
                .toString();
    }
}
