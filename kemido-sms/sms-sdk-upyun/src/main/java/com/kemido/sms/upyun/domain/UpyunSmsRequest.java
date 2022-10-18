package com.kemido.sms.upyun.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * <p>Description: 又拍网发送短信请求实体 </p>
 */
public class UpyunSmsRequest {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 模板编号
     */
    @JsonProperty("template_id")
    @JSONField(name = "template_id")
    private String templateId;

    /**
     * 短信参数
     */
    private String vars;

    /**
     * 拓展码
     */
    @JsonProperty("extend_code")
    @JSONField(name = "extend_code")
    private String extendCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mobile", mobile)
                .add("templateId", templateId)
                .add("vars", vars)
                .add("extendCode", extendCode)
                .toString();
    }
}
