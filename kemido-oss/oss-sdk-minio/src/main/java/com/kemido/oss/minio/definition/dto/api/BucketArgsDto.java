package com.kemido.oss.minio.definition.dto.api;

import io.minio.BucketArgs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * <p>Description: Minio 基础 Bucket Dto </p>
 */
public abstract class BucketArgsDto<B extends BucketArgs.Builder<B, A>, A extends BucketArgs> extends BaseArgsDto<B, A> {

    @NotNull(message = "存储桶名称不能为空")
    @Schema(name = "存储桶名称")
    private String bucketName;
    @Schema(name = "存储区域")
    private String region;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    protected void prepare(B builder) {
        builder.bucket(getBucketName());
        if (StringUtils.isNotBlank(getRegion())) {
            builder.region(getRegion());
        }
        super.prepare(builder);
    }

    @Override
    public A build() {
        B builder = getBuilder();
        prepare(builder);
        return builder.build();
    }
}
