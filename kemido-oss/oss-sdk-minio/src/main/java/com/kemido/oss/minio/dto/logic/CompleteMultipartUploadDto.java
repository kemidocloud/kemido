package com.kemido.oss.minio.dto.logic;

import com.kemido.oss.minio.definition.dto.logic.BaseObjectDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

/**
 * <p>Description: 完成分片上传 Dto </p>
 */
@Schema(name = "完成分片上传请求参数实体", title = "完成分片上传请求参数实体")
public class CompleteMultipartUploadDto extends BaseObjectDto {

    @NotBlank(message = "分片上传ID不能为空")
    @Schema(name = "上传ID", title = "该ID通过CreateMultipartUpload获取")
    private String uploadId;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
