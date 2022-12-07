package com.kemido.oss.autoconfigure;

import com.kemido.oss.minio.configuration.MinioConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 对象存储自动配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        MinioConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Oss Starter] Auto Configure.");
    }
}
