package com.kemido.oauth2.server.authorization.dto;

import com.kemido.assistant.core.definition.domain.AbstractDto;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * <p>Description: 加密创建请求 </p>
 */
@Schema(title = "加密创建请求")
public class SessionCreate extends AbstractDto {

    @NotBlank(message = "客户端ID不能为空")
    @Schema(title = "客户端ID")
    private String clientId;

    @NotBlank(message = "客户端秘钥不能为空")
    @Schema(title = "客户端秘钥")
    private String clientSecret;

    @Schema(title = "未登录前端身份标识")
    private String sessionId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clientId", clientId)
                .add("clientSecret", clientSecret)
                .add("sessionId", sessionId)
                .toString();
    }
}
