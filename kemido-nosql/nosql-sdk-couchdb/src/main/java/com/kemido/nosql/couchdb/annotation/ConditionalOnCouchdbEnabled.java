package com.kemido.nosql.couchdb.annotation;

import com.kemido.nosql.couchdb.condition.CouchdbEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Influxdb条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(CouchdbEnabledCondition.class)
public @interface ConditionalOnCouchdbEnabled {
}
