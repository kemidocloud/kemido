package com.kemido.cache.redis.configuration;

import com.kemido.cache.redis.annotation.ConditionalOnRedisSessionSharing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: 基于 Redis 的 Session 共享配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnRedisSessionSharing
public class RedisSessionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Cache Redis Session] Auto Configure.");
    }

    /**
     * 指定 flushMode 为 IMMEDIATE 表示立即将 session 写入 redis
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(HttpServletRequest.class)
    @EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
    public static class HttpSessionConfiguration {
        @PostConstruct
        public void postConstruct() {
            log.debug("[Kemido] |- SDK [Engine Cache Redis Http Session] Auto Configure.");
        }
    }
}
