package com.kemido.oss.minio.definition.dto.logic;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>Description: 扩展 Minio 应用Dto </p>
 */
public class BaseDto implements Serializable {

    @NotNull(message = "存储桶名称不能为空")
    @Schema(name = "存储桶名称")
    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
