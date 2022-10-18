package com.kemido.pay.alipay.support;

import com.kemido.pay.alipay.definition.AlipayProfile;
import com.kemido.pay.alipay.definition.AlipayProfileStorage;
import com.kemido.pay.alipay.properties.AlipayProperties;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * <p>Description: 支付宝配置默认存储 </p>
 */
public class AlipayDefaultProfileStorage extends AlipayProfileStorage {

    private final AlipayProperties alipayProperties;

    public AlipayDefaultProfileStorage(AlipayProperties alipayProperties) {
        this.alipayProperties = alipayProperties;
    }

    @Override
    public AlipayProfile getProfile(String identity) {
        Map<String, AlipayProfile> profiles = alipayProperties.getProfiles();
        if (MapUtils.isNotEmpty(profiles)) {
            return profiles.get(identity);
        }

        return null;
    }
}
