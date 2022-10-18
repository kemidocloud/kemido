package com.kemido.sms.upyun.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * <p>Description: 发送响应 </p>
 */
public class UpyunSmsResponse {

    /**
     * 所有手机号发送短信的结果
     */
    @JsonProperty("message_ids")
    @JSONField(name = "message_ids")
    private Collection<MessageId> messageIds;

    public Collection<MessageId> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(Collection<MessageId> messageIds) {
        this.messageIds = messageIds;
    }
}
