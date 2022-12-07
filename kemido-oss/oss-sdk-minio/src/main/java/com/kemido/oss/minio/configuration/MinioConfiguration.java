package com.kemido.oss.minio.configuration;

import com.kemido.oss.minio.annotation.ConditionalOnMinioEnabled;
import com.kemido.oss.minio.core.MinioAsyncClientObjectPool;
import com.kemido.oss.minio.core.MinioClientObjectPool;
import com.kemido.oss.minio.properties.MinioProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Minio配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMinioEnabled
@EnableConfigurationProperties({MinioProperties.class})
public class MinioConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MinioConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[Kemido] |- SDK [Oss Minio] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioClientObjectPool minioClientPool(MinioProperties minioProperties) {
        MinioClientObjectPool minioClientObjectPool = new MinioClientObjectPool(minioProperties);
        log.trace("[Kemido] |- Bean [Minio Client Pool] Auto Configure.");
        return minioClientObjectPool;
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioAsyncClientObjectPool minioAsyncClientPool(MinioProperties minioProperties) {
        MinioAsyncClientObjectPool minioAsyncClientObjectPool = new MinioAsyncClientObjectPool(minioProperties);
        log.trace("[Kemido] |- Bean [Minio Async Client Pool] Auto Configure.");
        return minioAsyncClientObjectPool;
    }

    @Configuration(proxyBeanMethods = false)
    @ComponentScan(basePackages = {
            "com.kemido.oss.minio.service",
            "com.kemido.oss.minio.processor",
            "com.kemido.oss.minio.controller",
    })
    static class MinioLogicConfiguration {
        @PostConstruct
        public void init() {
            log.debug("[Kemido] |- SDK [Oss Minio Logic] Auto Configure.");
        }
    }
}
