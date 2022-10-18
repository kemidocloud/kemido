package com.kemido.sms.all.annotation;

import com.kemido.cache.jetcache.annotation.EnableKemidoJetCache;
import com.kemido.sms.all.configuration.SmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启短信统一支持 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableKemidoJetCache
@Import(SmsConfiguration.class)
public @interface EnableKemidoSms {
}
