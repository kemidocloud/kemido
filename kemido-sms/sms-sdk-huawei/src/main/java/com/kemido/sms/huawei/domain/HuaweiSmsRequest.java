package com.kemido.sms.huawei.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * <p>Description: 华为云短信发送请求实体 </p>
 */
public class HuaweiSmsRequest implements Serializable {

    private String from;
    private String to;
    private String templateId;
    private String templateParas;
    private String signature;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateParas() {
        return templateParas;
    }

    public void setTemplateParas(String templateParas) {
        this.templateParas = templateParas;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("from", from)
                .add("to", to)
                .add("templateId", templateId)
                .add("templateParas", templateParas)
                .add("signature", signature)
                .toString();
    }
}
