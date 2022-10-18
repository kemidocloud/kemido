package com.kemido.sms.huawei.domain;

import com.google.common.base.MoreObjects;

import java.util.Collection;

/**
 * <p>Description: 华为云响应结果 </p>
 */
public class HuaweiSmsResponse {

    /**
     * 成功代码
     */
    public static final String SUCCESS_CODE = "000000";

    /**
     * 请求返回的结果码。
     */
    private String code;

    /**
     * 请求返回的结果码描述。
     */
    private String description;

    /**
     * 短信ID列表，当目的号码存在多个时，每个号码都会返回一个SmsID。
     * <p>
     * 当返回异常响应时不携带此字段。
     */
    private Collection<SmsID> result;

    /**
     * 短信ID
     */

    public static class SmsID {

        /**
         * 短信的唯一标识。
         */
        private String smsMsgId;

        /**
         * 短信发送方的号码。
         */
        private String from;

        /**
         * 短信接收方的号码。
         */
        private String originTo;

        /**
         * 短信状态码
         */
        private String status;

        /**
         * 短信资源的创建时间，即短信平台接收到客户发送短信请求的时间，为UTC时间。
         * <p>
         * 格式为：yyyy-MM-dd'T'HH:mm:ss'Z'。
         */
        private String createTime;

        public String getSmsMsgId() {
            return smsMsgId;
        }

        public void setSmsMsgId(String smsMsgId) {
            this.smsMsgId = smsMsgId;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getOriginTo() {
            return originTo;
        }

        public void setOriginTo(String originTo) {
            this.originTo = originTo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("smsMsgId", smsMsgId)
                    .add("from", from)
                    .add("originTo", originTo)
                    .add("status", status)
                    .add("createTime", createTime)
                    .toString();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<SmsID> getResult() {
        return result;
    }

    public void setResult(Collection<SmsID> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("description", description)
                .add("result", result)
                .toString();
    }
}
