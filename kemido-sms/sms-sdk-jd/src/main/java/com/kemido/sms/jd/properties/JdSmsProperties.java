package com.kemido.sms.jd.properties;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.core.definition.AbstractSmsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 京东云短信配置 </p>
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_JD)
public class JdSmsProperties extends AbstractSmsProperties {

    /**
     * AccessKey ID
     */
    private String accessKeyId;

    /**
     * AccessKey Secret
     */
    private String secretAccessKey;

    /**
     * 地域
     */
    private String region = "cn-north-1";

    /**
     * 签名ID
     */
    private String signId;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }
}
