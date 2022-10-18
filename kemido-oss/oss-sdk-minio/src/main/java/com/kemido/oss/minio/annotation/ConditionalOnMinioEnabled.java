package com.kemido.oss.minio.annotation;

import com.kemido.oss.minio.condition.MinioEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Minio是否开启条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(MinioEnabledCondition.class)
public @interface ConditionalOnMinioEnabled {
}
