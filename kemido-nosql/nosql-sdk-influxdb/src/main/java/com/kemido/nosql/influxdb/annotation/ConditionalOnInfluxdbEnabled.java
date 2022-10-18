package com.kemido.nosql.influxdb.annotation;

import com.kemido.nosql.influxdb.condition.InfluxdbEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Influxdb条件注解 </p>
 */
@Conditional(InfluxdbEnabledCondition.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConditionalOnInfluxdbEnabled {
}
