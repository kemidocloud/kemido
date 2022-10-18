package com.kemido.oss.minio.dto.logic;

import com.kemido.oss.minio.definition.dto.logic.BaseObjectDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;

/**
 * <p>Description: 创建分片上传Dto </p>
 */
@Schema(name = "创建分片上传请求参数实体", title = "创建分片上传请求参数实体")
public class CreateMultipartUpload extends BaseObjectDto {

    @Min(value = 1, message = "分片数量不能小于等于1")
    @Schema(name = "分片数量")
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
