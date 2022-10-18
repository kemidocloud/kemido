package com.kemido.oss.minio.dto.api.bucket;

import com.kemido.oss.minio.definition.dto.api.BucketArgsDto;
import io.minio.BucketExistsArgs;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 检查桶是否存在参数实体 </p>
 */
@Schema(name = "检查桶是否存在参数实体", title = "检查桶是否存在参数实体")
public class BucketExistsArgsDto extends BucketArgsDto<BucketExistsArgs.Builder, BucketExistsArgs> {

    @Override
    public BucketExistsArgs.Builder getBuilder() {
        return BucketExistsArgs.builder();
    }
}
