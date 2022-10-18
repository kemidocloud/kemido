package com.kemido.web.scan.annotation;

import com.kemido.web.scan.condition.RequestMappingScanCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 分布式架构模式条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(RequestMappingScanCondition.class)
public @interface ConditionalOnScanEnabled {
}
