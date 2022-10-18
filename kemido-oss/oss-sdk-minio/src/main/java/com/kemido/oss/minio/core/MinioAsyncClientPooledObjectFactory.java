package com.kemido.oss.minio.core;

import com.kemido.oss.minio.properties.MinioProperties;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>Description: 扩展的 Minio 异步 Client 池化工厂 </p>
 */
public class MinioAsyncClientPooledObjectFactory extends BasePooledObjectFactory<MinioAsyncClient> {

    private final MinioProperties minioProperties;

    public MinioAsyncClientPooledObjectFactory(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Override
    public MinioAsyncClient create() throws Exception {
        io.minio.MinioAsyncClient minioAsyncClient =  io.minio.MinioAsyncClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return new MinioAsyncClient(minioAsyncClient);
    }

    @Override
    public PooledObject<MinioAsyncClient> wrap(MinioAsyncClient minioAsyncClient) {
        return new DefaultPooledObject<>(minioAsyncClient);
    }
}
