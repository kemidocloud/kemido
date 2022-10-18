package com.kemido.oss.minio.core;

import com.kemido.oss.core.exception.OssClientPoolErrorException;
import com.kemido.oss.minio.properties.MinioProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: Minio 异步 Client 对象池 </p>
 */
public class MinioAsyncClientObjectPool {

    private static final Logger log = LoggerFactory.getLogger(MinioAsyncClientObjectPool.class);

    private final GenericObjectPool<MinioAsyncClient> minioAsyncClientPool;

    public MinioAsyncClientObjectPool(MinioProperties minioProperties) {
       MinioAsyncClientPooledObjectFactory factory = new MinioAsyncClientPooledObjectFactory(minioProperties);

        GenericObjectPoolConfig<MinioAsyncClient> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(minioProperties.getPool().getMaxTotal());
        config.setMaxIdle(minioProperties.getPool().getMaxIdle());
        config.setMinIdle(minioProperties.getPool().getMinIdle());
        config.setMaxWait(minioProperties.getPool().getMaxWait());
        config.setMinEvictableIdleTime(minioProperties.getPool().getMinEvictableIdleTime());
        config.setSoftMinEvictableIdleTime(minioProperties.getPool().getSoftMinEvictableIdleTime());
        config.setLifo(minioProperties.getPool().getLifo());
        config.setBlockWhenExhausted(minioProperties.getPool().getBlockWhenExhausted());
        minioAsyncClientPool = new GenericObjectPool<>(factory, config);
    }

    public MinioAsyncClient getMinioClient() throws OssClientPoolErrorException {
        try {
            MinioAsyncClient minioAsyncClient = minioAsyncClientPool.borrowObject();
            log.debug("[Kemido] |- Fetch minio async client from object pool.");
            return minioAsyncClient;
        } catch (Exception e) {
            log.error("[Kemido] |- Can not fetch minio client from pool.");
            throw new OssClientPoolErrorException("Can not fetch minio async client from pool.");
        }
    }


    public void close(MinioAsyncClient minioAsyncClient) {
        if (ObjectUtils.isNotEmpty(minioAsyncClient)) {
            minioAsyncClientPool.returnObject(minioAsyncClient);
        }
    }
}
