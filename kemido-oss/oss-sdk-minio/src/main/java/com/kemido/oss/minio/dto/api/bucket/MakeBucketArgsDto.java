package com.kemido.oss.minio.dto.api.bucket;

import com.kemido.oss.minio.definition.dto.api.BucketArgsDto;
import io.minio.MakeBucketArgs;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 创建桶是参数实体 </p>
 *
 * 与 Create Bucket 不同，这里仅是创建，Create Bucket 包含桶是否存在检查
 */
@Schema(name = "创建桶是参数实体", title = "创建桶是参数实体")
public class MakeBucketArgsDto extends BucketArgsDto<MakeBucketArgs.Builder, MakeBucketArgs> {

    @Override
    public MakeBucketArgs.Builder getBuilder() {
        return MakeBucketArgs.builder();
    }
}
