package com.kemido.sms.jd.annotation;

import com.kemido.sms.jd.condition.JdSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 京东云短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(JdSmsEnabledCondition.class)
public @interface ConditionalOnJdSmsEnabled {
}
