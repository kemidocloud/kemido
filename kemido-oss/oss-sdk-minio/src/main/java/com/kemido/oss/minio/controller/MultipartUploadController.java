package com.kemido.oss.minio.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.oss.minio.domain.MultipartUploadCreate;
import com.kemido.oss.minio.dto.logic.CompleteMultipartUploadDto;
import com.kemido.oss.minio.dto.logic.CreateMultipartUpload;
import com.kemido.oss.minio.processor.MultipartUploadProcessor;
import com.kemido.protect.core.annotation.Idempotent;
import com.kemido.rest.core.controller.Controller;
import io.minio.ObjectWriteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: Minio 分片上传接口 </p>
 */
@RestController
@RequestMapping("/oss/minio/multipart")
@Tags({
        @Tag(name = "对象存储管理接口"),
        @Tag(name = "Minio 对象存储管理接口"),
        @Tag(name = "Minio 对象存储分片上传接口")
})
public class MultipartUploadController implements Controller {

    private final MultipartUploadProcessor multipartUploadProcessor;

    public MultipartUploadController(MultipartUploadProcessor multipartUploadProcessor) {
        this.multipartUploadProcessor = multipartUploadProcessor;
    }

    @Idempotent
    @Operation(summary = "创建分片上传信息", description = "创建分片上传信息",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "domain", required = true, description = "Create Multipart Upload 请求实体", schema = @Schema(implementation = CreateMultipartUpload.class))
    })
    @PostMapping("/create")
    public Result<MultipartUploadCreate> createMultipartUpload(@Validated @RequestBody CreateMultipartUpload domain) {
        MultipartUploadCreate result = multipartUploadProcessor.createMultipartUpload(domain.getBucketName(), domain.getObjectName(), domain.getSize());
        return result(result);
    }

    @Idempotent
    @Operation(summary = "完成分片上传", description = "完成分片上传，Minio将上传完成的分片信息进行合并",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "bucketName", required = true, description = "存储桶名称"),
            @Parameter(name = "objectName", required = true, description = "文件名称"),
            @Parameter(name = "objectName", required = true, description = "文件名称"),
    })
    @PostMapping("/complete")
    public Result<String> completeMultipartUpload(@Validated @RequestBody CompleteMultipartUploadDto domain) {
        ObjectWriteResponse objectWriteResponse = multipartUploadProcessor.completeMultipartUpload(domain.getBucketName(), domain.getObjectName(), domain.getUploadId());
        if (ObjectUtils.isNotEmpty(objectWriteResponse)) {
            return result(true);
        } else {
            return result(false);
        }
    }

}
