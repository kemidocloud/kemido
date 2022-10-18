package com.kemido.data.jpa.annotation;

import com.kemido.data.jpa.condition.MultiTenancyEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Influxdb条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MultiTenancyEnabledCondition.class)
public @interface ConditionalOnMultiTenancyEnabled {
}
