package com.kemido.oss.minio.definition.dto.logic;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * <p>Description: 扩展对象操作Dto </p>
 */
public class BaseObjectDto extends BaseBucketDto{

    @NotNull(message = "对象名称不能为空")
    @Schema(name = "对象名称")
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
