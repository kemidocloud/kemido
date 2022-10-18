package com.kemido.cache.redisson.annotation;

import com.kemido.cache.redisson.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启Redisson注入 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedissonConfiguration.class)
public @interface EnableKemidoRedisson {
}
