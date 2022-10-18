package com.kemido.oss.minio.dto.logic;

import com.kemido.oss.minio.definition.dto.logic.BaseBucketDto;
import com.kemido.rest.core.definition.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * <p>Description: 创建桶请求参数实体 </p>
 */
@Schema(name = "创建桶请求参数实体", title = "创建桶请求参数实体")
public class CreateBucketDto extends BaseBucketDto {

    @NotBlank(message = "存储桶名称不能为空")
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
}
