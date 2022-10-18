package com.kemido.sms.yunpian.properties;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.core.definition.AbstractSmsProperties;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 云片网短信配置 </p>
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_YUNPIAN)
public class YunpianSmsProperties extends AbstractSmsProperties {

    /**
     * apikey
     */
    private String apikey;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("apikey", apikey)
                .toString();
    }
}
