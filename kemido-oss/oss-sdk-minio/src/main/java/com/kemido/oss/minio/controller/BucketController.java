package com.kemido.oss.minio.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.oss.minio.domain.MinioBucket;
import com.kemido.oss.minio.dto.logic.CreateBucketDto;
import com.kemido.oss.minio.dto.api.bucket.MakeBucketArgsDto;
import com.kemido.oss.minio.dto.api.bucket.RemoveBucketArgsDto;
import com.kemido.oss.minio.service.BucketService;
import com.kemido.protect.core.annotation.AccessLimited;
import com.kemido.protect.core.annotation.Idempotent;
import com.kemido.rest.core.controller.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: Minio 对象存储 Bucket 管理接口 </p>
 */
@RestController
@RequestMapping("/oss/minio/bucket")
@Tags({
        @Tag(name = "对象存储管理接口"),
        @Tag(name = "Minio 对象存储管理接口"),
        @Tag(name = "Minio 对象存储Bucket管理接口")
})
public class BucketController implements Controller {

    private final BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @AccessLimited
    @Operation(summary = "获取全部Bucket接口", description = "获取全部Bucket接口")
    @GetMapping("/list")
    public Result<List<MinioBucket>> findAll() {
        List<MinioBucket> buckets = bucketService.listBuckets();
        return result(buckets);
    }

    @Idempotent
    @Operation(summary = "创建Bucket接口", description = "创建Bucket接口，该接口仅是创建，不包含是否已存在检查",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "domain", required = true, description = "Make Bucket 请求实体", schema = @Schema(implementation = MakeBucketArgsDto.class))
    })
    @PostMapping
    public Result<String> make(@Validated @RequestBody MakeBucketArgsDto domain) {
        bucketService.makeBucket(domain.build());
        return result(true);
    }

    @Idempotent
    @Operation(summary = "创建Bucket接口", description = "创建Bucket接口，该接口包含Bucket是否已存在检查",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "domain", required = true, description = "Make Bucket 请求实体", schema = @Schema(implementation = CreateBucketDto.class))
    })
    @PostMapping("/create")
    public Result<String> create(@Validated @RequestBody CreateBucketDto domain) {
        if (StringUtils.isNotBlank(domain.getRegion())) {
            bucketService.createBucket(domain.getBucketName(), domain.getRegion());
        } else {
            bucketService.createBucket(domain.getBucketName());
        }
        return result(true);
    }

    @Idempotent
    @Operation(summary = "删除Bucket接口", description = "根据Bucket 名称删除数据，可指定 Region",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json")),
            responses = {@ApiResponse(description = "操作消息", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "domain", required = true, description = "Remote Bucket 请求实体", schema = @Schema(implementation = RemoveBucketArgsDto.class))
    })
    @DeleteMapping
    public Result<String> delete(@Validated @RequestBody RemoveBucketArgsDto domain) {
        bucketService.removeBucket(domain.build());
        return result(true);
    }
}
