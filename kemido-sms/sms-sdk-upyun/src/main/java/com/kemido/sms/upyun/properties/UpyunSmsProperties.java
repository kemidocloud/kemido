package com.kemido.sms.upyun.properties;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.core.definition.AbstractSmsProperties;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 又拍云短信配置 </p>
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_UPYUN)
public class UpyunSmsProperties extends AbstractSmsProperties {

    /**
     * token
     */
    private String token;

    private String apiUrl = "https://sms-api.upyun.com/api/messages";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .add("apiUrl", apiUrl)
                .toString();
    }
}
