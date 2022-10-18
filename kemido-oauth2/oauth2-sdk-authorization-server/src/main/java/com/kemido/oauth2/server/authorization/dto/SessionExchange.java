package com.kemido.oauth2.server.authorization.dto;

import com.kemido.assistant.core.definition.domain.AbstractDto;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * <p>Description: 机要传递实体 </p>
 */
@Schema(title = "机要传递实体")
public class SessionExchange extends AbstractDto {

    @NotBlank(message = "confidential参数不能为空")
    @Schema(title = "用后端RSA PublicKey加密的前端RSA PublicKey")
    private String confidential;

    @NotBlank(message = "Session Key不能为空")
    @Schema(title = "未登录前端身份标识")
    private String sessionId;

    public String getConfidential() {
        return confidential;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential;
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
                .add("confidential", confidential)
                .add("sessionId", sessionId)
                .toString();
    }
}
