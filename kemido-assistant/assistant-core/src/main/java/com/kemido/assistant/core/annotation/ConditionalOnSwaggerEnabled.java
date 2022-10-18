package com.kemido.assistant.core.annotation;

import com.kemido.assistant.core.conditon.SwaggerEnabledCondition;
import com.kemido.assistant.core.constants.BaseConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Swagger条件开启主机 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SwaggerEnabledCondition.class)
public @interface ConditionalOnSwaggerEnabled {
}
