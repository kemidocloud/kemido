package com.kemido.oss.minio.dto.api.bucket;

import com.kemido.oss.minio.definition.dto.api.BucketArgsDto;
import io.minio.RemoveBucketArgs;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 删除桶参数实体 </p>
 */
@Schema(name = "删除桶参数实体", title = "删除桶参数实体")
public class RemoveBucketArgsDto extends BucketArgsDto<RemoveBucketArgs.Builder, RemoveBucketArgs> {
    @Override
    public RemoveBucketArgs.Builder getBuilder() {
        return RemoveBucketArgs.builder();
    }
}
