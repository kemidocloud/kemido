package com.kemido.protect.web.annotation;

import com.kemido.cache.jetcache.annotation.EnableKemidoJetCache;
import com.kemido.protect.web.configuration.HttpCryptoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启 Rest Crypto </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableKemidoJetCache
@Import(HttpCryptoConfiguration.class)
public @interface EnableKemidoRestCrypto {
}
