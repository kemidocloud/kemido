package com.kemido.web.rest.annotation;

import com.kemido.web.rest.condition.FeignUseHttpClientCondition;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Feign 使用 HttpClient 客户端 条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnClass(ApacheHttpClient.class)
@ConditionalOnMissingBean(CloseableHttpClient.class)
@Conditional(FeignUseHttpClientCondition.class)
public @interface ConditionalOnFeignUseHttpClient {
}
