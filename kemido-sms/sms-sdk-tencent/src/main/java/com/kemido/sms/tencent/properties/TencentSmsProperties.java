package com.kemido.sms.tencent.properties;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.core.definition.AbstractSmsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 腾讯云短信配置 </p>
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_TENCENT)
public class TencentSmsProperties extends AbstractSmsProperties {

    /**
     * 腾讯云 SecretID
     */
    private String secretId;

    /**
     * 腾讯云 SecretKey
     */
    private String secretKey;

    /**
     * 短信签名
     */
    private String region;

    /**
     * 短信应用ID
     */
    private String smsAppId;

    /**
     * 短信签名
     */
    private String smsSign;

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSmsAppId() {
        return smsAppId;
    }

    public void setSmsAppId(String smsAppId) {
        this.smsAppId = smsAppId;
    }

    public String getSmsSign() {
        return smsSign;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }
}
