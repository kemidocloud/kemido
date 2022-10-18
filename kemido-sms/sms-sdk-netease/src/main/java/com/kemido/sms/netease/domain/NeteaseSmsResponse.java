package com.kemido.sms.netease.domain;

import com.google.common.base.MoreObjects;

/**
 * <p>Description: 响应结果 </p>
 */
public class NeteaseSmsResponse {

    /**
     * 成功代码
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 请求返回的结果码。
     */
    private int code;

    /**
     * 请求返回的结果码描述。
     */
    private String msg;

    private Long obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getObj() {
        return obj;
    }

    public void setObj(Long obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("msg", msg)
                .add("obj", obj)
                .toString();
    }
}
