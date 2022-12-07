package com.kemido.cache.redis.configuration;

import com.kemido.cache.redis.annotation.ConditionalOnRedisSessionSharing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 基于 Redis 的 Session 共享配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnRedisSessionSharing
public class RedisSessionSharingConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Cache Redis Session Sharing] Auto Configure.");
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
    static class HerodotusRedisHttpSessionConfiguration {
    }
}
