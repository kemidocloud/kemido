package com.kemido.pay.wechat.properties;

import com.kemido.pay.core.constants.PayConstants;
import com.kemido.pay.wechat.definition.WxpayProfile;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * <p>Description: 微信支付配置属性 </p>
 */
@ConfigurationProperties(prefix = PayConstants.PROPERTY_PAY_WXPAY)
public class WxpayProperties {
    /**
     * 是否开启微信支付使用
     */
    private Boolean enabled;
    /**
     * 是否是使用微信支付仿真环境
     */
    private Boolean sandbox = false;
    /**
     * 默认的 Profile 自定义唯一标识 Key
     */
    private String defaultProfile;
    /**
     * 支付宝支付信息配置，支持多个。以自定义唯一标识作为 Key。
     */
    private Map<String, WxpayProfile> profiles;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getSandbox() {
        return sandbox;
    }

    public void setSandbox(Boolean sandbox) {
        this.sandbox = sandbox;
    }

    public String getDefaultProfile() {
        return defaultProfile;
    }

    public void setDefaultProfile(String defaultProfile) {
        this.defaultProfile = defaultProfile;
    }

    public Map<String, WxpayProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Map<String, WxpayProfile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enabled", enabled)
                .add("sandbox", sandbox)
                .add("defaultProfile", defaultProfile)
                .toString();
    }
}
