package com.kemido.sms.upyun.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 手机号发送短信的结果 </p>
 */
public class MessageId {

    /**
     * 错误情况
     */
    @JsonProperty("error_code")
    @JSONField(name = "error_code")
    private String errorCode;

    /**
     * 旧版本国内短信的 message 编号
     */
    @JsonProperty("message_id")
    @JSONField(name = "message_id")
    private Integer messageId;

    /**
     * message 编号
     */
    @JsonProperty("msg_id")
    @JSONField(name = "msg_id")
    private String msgId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 判断是否成功
     *
     * @return 是否成功
     */
    public boolean succeed() {
        return StringUtils.isBlank(errorCode) && StringUtils.isNotBlank(msgId);
    }
}
