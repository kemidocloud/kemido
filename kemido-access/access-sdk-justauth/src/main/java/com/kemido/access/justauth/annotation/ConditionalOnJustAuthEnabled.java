package com.kemido.access.justauth.annotation;

import com.kemido.access.justauth.condition.JustAuthEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: JustAuth开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(JustAuthEnabledCondition.class)
public @interface ConditionalOnJustAuthEnabled {
}
