package com.kemido.assistant.core.definition.http;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.MsgConvertor;
import cn.zhxu.okhttps.jackson.JacksonMsgConvertor;

/**
 * <p>File: AbstractRestApiService </p>
 *
 * <p>Description: 外部Rest API抽象服务 </p>
 */
public abstract class AbstractRest {

    /**
     * 获取外部Rest API基础地址
     * @return 访问接口的统一BaseURL
     */
    protected abstract String getBaseUrl();

    protected HTTP http() {
        return HTTP.builder()
                .baseUrl(getBaseUrl())
                .addMsgConvertor(getMsgConvertor())
                .build();
    }

    protected MsgConvertor getMsgConvertor() {
        return new JacksonMsgConvertor();
    }
}
