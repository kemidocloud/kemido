package com.kemido.web.rest.annotation;

import com.kemido.web.rest.condition.FeignUseOkHttpCondition;
import feign.okhttp.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Feign 使用 OkHttp 客户端 条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnClass(OkHttpClient.class)
@ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
@Conditional(FeignUseOkHttpCondition.class)
public @interface ConditionalOnFeignUseOkHttp {
}
