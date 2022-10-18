package com.kemido.oss.minio.definition.dto.logic;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 扩展桶操作Dto</p>
 */
public class BaseBucketDto extends BaseDto {

    @Schema(name = "存储区域")
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
