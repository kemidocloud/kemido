package com.kemido.oauth2.server.authorization.dto;

import com.kemido.assistant.core.definition.domain.AbstractDto;
import com.google.common.base.MoreObjects;

/**
 * <p>Description: Session响应实体 </p>
 */
public class Session extends AbstractDto {

    /**
     * 前端未登录时，唯一身份标识。如果由前端生成，则直接返回；如果由后端生成，则返回后端生成值
     */
    private String sessionId;

    /**
     * 后台RSA公钥
     */
    private String publicKey;

    /**
     * 本系统授权码模式校验参数
     */
    private String state;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sessionId", sessionId)
                .add("publicKey", publicKey)
                .toString();
    }
}
