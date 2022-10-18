package com.kemido.oss.minio.definition.service;

import com.kemido.oss.minio.core.MinioClientObjectPool;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Description: Minio 基础服务 </p>
 */
public abstract class BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(BaseMinioService.class);

    @Autowired
    private MinioClientObjectPool minioClientObjectPool;

    protected MinioClient getMinioClient() {
        return minioClientObjectPool.getMinioClient();
    }


    protected void close(MinioClient minioClient) {
        minioClientObjectPool.close(minioClient);
    }
}
