package com.kemido.oauth2.compliance.dto;

import com.kemido.rest.core.definition.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * <p>Description: 登录提示信息 </p>
 */
@Schema(title = "登录错误提示信息")
public class SignInErrorPrompt extends BaseDto {

    @NotBlank(message = "登录用户名不能为空")
    @Schema(name = "登录用户名", title = "必须是有效的用户名")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
