package com.kemido.sms.chinamobile.domain;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>Description: 移动短信发送请求实体 </p>
 */
public class ChinaMobileSmsRequest {

    private final String ecName;
    private final String apId;
    private final String templateId;
    private final String mobiles;
    private final String params;
    private final String sign;
    private final String addSerial;
    private final String mac;

    public ChinaMobileSmsRequest(String ecName, String apId, String secretKey, String templateId, String mobiles, String params, String sign) {
        this.ecName = ecName;
        this.apId = apId;
        this.templateId = templateId;
        this.mobiles = mobiles;
        this.params = params;
        this.sign = sign;
        this.addSerial = "";
        this.mac = this.generateMac(ecName, apId, secretKey, templateId, mobiles, params, sign);
    }


    private String generateMac(String ecName, String apId, String secretKey, String templateId, String mobiles, String params, String sign) {
        String origin = ecName + apId + secretKey + templateId + mobiles + params + sign;
        return DigestUtils.md5Hex(origin);
    }

    public String getEcName() {
        return ecName;
    }

    public String getApId() {
        return apId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getMobiles() {
        return mobiles;
    }

    public String getParams() {
        return params;
    }

    public String getSign() {
        return sign;
    }

    public String getAddSerial() {
        return addSerial;
    }

    public String getMac() {
        return mac;
    }
}
