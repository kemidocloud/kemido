package com.kemido.oss.minio.definition.dto.api;

import io.minio.ObjectArgs;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * <p>Description: Minio 基础 Object Dto </p>
 */
public abstract class ObjectArgsDto<B extends ObjectArgs.Builder<B, A>, A extends ObjectArgs> extends BucketArgsDto<B, A> {

    @NotNull(message = "对象名称不能为空")
    @Schema(name = "对象名称")
    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    protected void prepare(B builder) {
        builder.object(getObjectName());
        super.prepare(builder);
    }
}
