package com.kemido.sms.chinamobile.domain;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: 响应结果 </p>
 */
public class ChinaMobileSmsResult {

    /**
     * 响应状态
     */
    public static final String SUCCESS_RSPCOD = "success";

    /**
     * 消息批次号，由云MAS平台生成，用于关联短信发送请求与状态报告，注：若数据验证不通过，该参数值为空
     */
    private String msgGroup;

    /**
     * 响应状态
     */
    private String rspcod;

    /**
     * 是否成功
     */
    private boolean success;

    public String getMsgGroup() {
        return msgGroup;
    }

    public void setMsgGroup(String msgGroup) {
        this.msgGroup = msgGroup;
    }

    public String getRspcod() {
        return rspcod;
    }

    public void setRspcod(String rspcod) {
        this.rspcod = rspcod;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("msgGroup", msgGroup)
                .add("rspcod", rspcod)
                .add("success", success)
                .toString();
    }
}
