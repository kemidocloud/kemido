package com.kemido.sms.chinamobile.properties;

import com.kemido.sms.core.constants.SmsConstants;
import com.kemido.sms.core.definition.AbstractSmsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 中国移动云短信配置 </p>
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_CHINA_MOBILE)
public class ChinaMobileSmsProperties extends AbstractSmsProperties {

    /**
     * 请求地址
     */
    private String uri = "http://112.35.1.155:1992/sms/tmpsubmit";

    /**
     * 企业名称
     */
    private String ecName;

    /**
     * 接口账号用户名
     */
    private String apId;

    /**
     * 接口账号密码
     */
    private String secretKey;

    /**
     * 签名编码。在模板短信控制台概览页获取。
     */
    private String sign;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
