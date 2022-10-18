package com.kemido.oss.minio.core;


import com.kemido.oss.core.exception.OssClientPoolErrorException;
import com.kemido.oss.minio.properties.MinioProperties;
import io.minio.MinioClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: Minio 客户端连接池 </p>
 */
public class MinioClientObjectPool {

    private static final Logger log = LoggerFactory.getLogger(MinioClientObjectPool.class);

    private final GenericObjectPool<MinioClient> minioClientPool;

    public MinioClientObjectPool(MinioProperties minioProperties) {

        MinioClientPooledObjectFactory factory = new MinioClientPooledObjectFactory(minioProperties);

        GenericObjectPoolConfig<MinioClient> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(minioProperties.getPool().getMaxTotal());
        config.setMaxIdle(minioProperties.getPool().getMaxIdle());
        config.setMinIdle(minioProperties.getPool().getMinIdle());
        config.setMaxWait(minioProperties.getPool().getMaxWait());
        config.setMinEvictableIdleTime(minioProperties.getPool().getMinEvictableIdleTime());
        config.setSoftMinEvictableIdleTime(minioProperties.getPool().getSoftMinEvictableIdleTime());
        config.setLifo(minioProperties.getPool().getLifo());
        config.setBlockWhenExhausted(minioProperties.getPool().getBlockWhenExhausted());
        minioClientPool = new GenericObjectPool<>(factory, config);
    }

    public MinioClient getMinioClient() throws OssClientPoolErrorException {
        try {
            MinioClient minioClient = minioClientPool.borrowObject();
            log.debug("[Kemido] |- Fetch minio client from object pool.");
            return minioClient;
        } catch (Exception e) {
            log.error("[Kemido] |- Can not fetch minio client from pool.");
            throw new OssClientPoolErrorException("Can not fetch minio client from pool.");
        }
    }

    public void close(MinioClient minioClient) {
        if (ObjectUtils.isNotEmpty(minioClient)) {
            minioClientPool.returnObject(minioClient);
        }
    }
}
