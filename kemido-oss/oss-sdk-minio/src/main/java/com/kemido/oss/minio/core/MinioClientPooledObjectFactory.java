package com.kemido.oss.minio.core;

import com.kemido.oss.minio.properties.MinioProperties;
import io.minio.MinioClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>Description: Minio 基础 Client 池化工厂 </p>
 */
public class MinioClientPooledObjectFactory extends BasePooledObjectFactory<MinioClient> {

    private final MinioProperties minioProperties;

    public MinioClientPooledObjectFactory(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Override
    public MinioClient create() throws Exception {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

    @Override
    public PooledObject<MinioClient> wrap(MinioClient minioClient) {
        return new DefaultPooledObject<>(minioClient);
    }
}
