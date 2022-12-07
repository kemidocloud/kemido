package com.kemido.cache.jetcache.configuration;

import com.kemido.cache.caffeine.configuration.CaffeineConfiguration;
import com.kemido.cache.core.properties.CacheProperties;
import com.kemido.cache.jetcache.enhance.KemidoCacheManager;
import com.kemido.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import com.kemido.cache.jetcache.utils.JetCacheUtils;
import com.kemido.cache.redis.configuration.RedisConfiguration;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;

/**
 * <p>Description: JetCacheConfiguration </p>
 * <p>
 * 新增JetCache配置，解决JetCache依赖循环问题
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CacheProperties.class)
@Import({CaffeineConfiguration.class, RedisConfiguration.class})
@AutoConfigureAfter(JetCacheAutoConfiguration.class)
public class JetCacheConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JetCacheConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Cache JetCache] Auto Configure.");
    }

    @Bean
    @ConditionalOnClass(CacheManager.class)
    public JetCacheCreateCacheFactory jetCacheCreateCacheFactory(CacheManager jcCacheManager) {
        JetCacheCreateCacheFactory factory = new JetCacheCreateCacheFactory(jcCacheManager);
        JetCacheUtils.setJetCacheCreateCacheFactory(factory);
        log.trace("[Kemido] |- Bean [Jet Cache Create Cache Factory] Auto Configure.");
        return factory;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public KemidoCacheManager kemidoCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
        KemidoCacheManager kemidoCacheManager = new KemidoCacheManager(jetCacheCreateCacheFactory, cacheProperties);
        kemidoCacheManager.setAllowNullValues(cacheProperties.getAllowNullValues());
        log.trace("[Kemido] |- Bean [Jet Cache Kemido Cache Manager] Auto Configure.");
        return kemidoCacheManager;
    }
}
