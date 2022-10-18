package com.kemido.cache.caffeine.configuration;

import com.kemido.cache.caffeine.enhance.KemidoCaffeineCacheManager;
import com.kemido.cache.core.properties.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Caffeine 配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class CaffeineConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CaffeineConfiguration.class);

    @Autowired
    private CacheProperties cacheProperties;

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Cache Caffeine] Auto Configure.");
    }

    @Bean
    public Caffeine<Object, Object> caffeine() {
        Caffeine<Object, Object> caffeine = Caffeine
                .newBuilder()
                .expireAfterWrite(cacheProperties.getDuration(), cacheProperties.getUnit());

        log.trace("[Kemido] |- Bean [Caffeine] Auto Configure.");

        return caffeine;
    }

    @Bean
    @ConditionalOnMissingBean(CaffeineCacheManager.class)
    public CaffeineCacheManager caffeineCacheManager(Caffeine<Object, Object> caffeine) {
        KemidoCaffeineCacheManager kemidoCaffeineCacheManager = new KemidoCaffeineCacheManager(cacheProperties);
        kemidoCaffeineCacheManager.setCaffeine(caffeine);
        log.trace("[Kemido] |- Bean [Caffeine Cache Manager] Auto Configure.");
        return kemidoCaffeineCacheManager;
    }
}
