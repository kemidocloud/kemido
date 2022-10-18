package com.kemido.oss.minio.definition;

import io.minio.BaseArgs;

import java.io.Serializable;

/**
 * <p>Description: Minio 参数构建器 </p>
 */
public interface MinioArgumentBuilder<B extends BaseArgs.Builder<B, A>, A extends BaseArgs> extends Serializable {

    /**
     * 构建 Minio 参数对象
     * @return Minio 参数对象
     */
    A build();

    /**
     * 获取Builder
     * @return builder
     */
    B getBuilder();
}
