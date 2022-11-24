package com.kemido.cache.redis.configuration;

import com.kemido.cache.redis.annotation.ConditionalOnRedisSessionSharing;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>Description: 基于 Redis 的 Session 共享配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnRedisSessionSharing
@EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
public class RedisSessionSharingConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Cache Redis Session Sharing] Auto Configure.");
    }

    
    @Bean
    @ConditionalOnMissingBean
    public HttpSessionListener httpSessionListener() {
        HttpSessionListener httpSessionListener = new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent httpSessionEvent) {
                HttpSession httpSession = httpSessionEvent.getSession();
                log.info("[Kemido] |- Session is CREATED, is [{}].", ObjectUtils.isNotEmpty(httpSession) ? httpSession.getId(): "");
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
                HttpSession httpSession = httpSessionEvent.getSession();
                log.info("[Kemido] |- Session is DESTROYED, is [{}].", httpSession.getId());
            }
        };
        log.trace("[Kemido] |- Bean [Http Session Listener] Auto Configure.");
        return httpSessionListener;
    }
}
