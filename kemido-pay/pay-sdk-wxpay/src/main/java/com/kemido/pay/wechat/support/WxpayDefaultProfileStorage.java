package com.kemido.pay.wechat.support;

import com.kemido.pay.wechat.definition.WxpayProfile;
import com.kemido.pay.wechat.definition.WxpayProfileStorage;
import com.kemido.pay.wechat.properties.WxpayProperties;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>Description: 微信支付配置，配置文件文件存储 </p>
 */
@Component
public class WxpayDefaultProfileStorage extends WxpayProfileStorage {

    private final WxpayProperties wxpayProperties;

    public WxpayDefaultProfileStorage(WxpayProperties wxpayProperties) {
        this.wxpayProperties = wxpayProperties;
    }

    private WxpayProperties getWxpayProperties() {
        return wxpayProperties;
    }

    @Override
    public WxpayProfile getProfile(String identity) {
        Map<String, WxpayProfile> profiles = getWxpayProperties().getProfiles();
        if (MapUtils.isNotEmpty(profiles)) {
            return profiles.get(identity);
        }

        return null;
    }
}
