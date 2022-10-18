package com.kemido.oss.minio.definition.service;

import com.kemido.oss.minio.core.MinioAsyncClient;
import com.kemido.oss.minio.core.MinioAsyncClientObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Description: Minio 基础异步服务 </p>
 */
public abstract class BaseMinioAsyncService {

    private static final Logger log = LoggerFactory.getLogger(BaseMinioAsyncService.class);

    @Autowired
    private MinioAsyncClientObjectPool minioAsyncClientObjectPool;

    protected MinioAsyncClient getMinioClient() {
        return minioAsyncClientObjectPool.getMinioClient();
    }

    protected void close(MinioAsyncClient minioAsyncClient) {
        minioAsyncClientObjectPool.close(minioAsyncClient);
    }
}
