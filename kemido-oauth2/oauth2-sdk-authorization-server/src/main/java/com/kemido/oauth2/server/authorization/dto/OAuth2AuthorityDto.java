package com.kemido.oauth2.server.authorization.dto;

import com.kemido.rest.core.definition.dto.BaseDto;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * <p>Description: OAuth2 Authority Dto </p>
 */
@Schema(name = "OAuth2 权限请求 Dto")
public class OAuth2AuthorityDto extends BaseDto {

    @Schema(name = "权限ID")
    @NotNull(message = "权限ID不能为空")
    private String authorityId;

    @Schema(name = "权限代码")
    @NotNull(message = "权限代码不能为空")
    private String authorityCode;

    @Schema(name = "服务ID")
    @NotNull(message = "服务ID不能为空")
    private String serviceId;

    @Schema(name = "请求方法")
    @NotNull(message = "请求方法不能为空")
    private String requestMethod;

    @Schema(name = "请求URL")
    @NotNull(message = "请求URL不能为空")
    private String url;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("authorityId", authorityId)
                .add("authorityCode", authorityCode)
                .add("serviceId", serviceId)
                .add("requestMethod", requestMethod)
                .add("url", url)
                .toString();
    }
}
