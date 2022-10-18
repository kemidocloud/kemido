package com.kemido.web.core.domain;

import com.kemido.assistant.core.definition.domain.AbstractEntity;
import com.kemido.assistant.core.enums.AuthorityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * <p>Description: Controller 请求注解元数据封装实体 </p>
 */
public class RequestMapping extends AbstractEntity {

    @JsonProperty("authorityId")
    private String metadataId;

    @JsonProperty("authorityCode")
    private String metadataCode;

    @JsonProperty("authorityName")
    private String metadataName;

    private String requestMethod;

    private String serviceId;

    private String className;

    private String methodName;

    private String url;

    private String parentId;

    private String description;

    public RequestMapping() {
    }

    @JsonProperty("authorityType")
    private AuthorityType authorityType = AuthorityType.API;

    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }

    public String getMetadataCode() {
        return metadataCode;
    }

    public void setMetadataCode(String metadataCode) {
        this.metadataCode = metadataCode;
    }

    public String getMetadataName() {
        return metadataName;
    }

    public void setMetadataName(String metadataName) {
        this.metadataName = metadataName;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("metadataId", metadataId)
                .add("metadataCode", metadataCode)
                .add("metadataName", metadataName)
                .add("requestMethod", requestMethod)
                .add("serviceId", serviceId)
                .add("className", className)
                .add("methodName", methodName)
                .add("url", url)
                .add("parentId", parentId)
                .add("description", description)
                .add("authorityType", authorityType)
                .toString();
    }
}
