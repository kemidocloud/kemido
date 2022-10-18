package com.kemido.cache.redis.annotation;

import com.kemido.cache.redis.condition.RedisSessionSharingCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 基于 Redis Session 共享条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(RedisSessionSharingCondition.class)
public @interface ConditionalOnRedisSessionSharing {
}
