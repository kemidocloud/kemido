package com.kemido.websocket.accelerator.domain;

import com.google.common.base.MoreObjects;

import java.security.Principal;

/**
 * <p>Description: Websocket登录连接对象 </p>
 * <p>
 * 用于保存websocket连接过程中需要存储的业务参数
 */
public class WebSocketPrincipal implements Principal {

    private String token;

    public WebSocketPrincipal(String token) {
        this.token = token;
    }

    @Override
    public String getName() {
        return this.token;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .toString();
    }
}
