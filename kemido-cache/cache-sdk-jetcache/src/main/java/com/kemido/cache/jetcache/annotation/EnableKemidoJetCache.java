package com.kemido.cache.jetcache.annotation;

import com.kemido.cache.jetcache.configuration.JetCacheConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启JetCache注入 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JetCacheConfiguration.class)
public @interface EnableKemidoJetCache {
}
