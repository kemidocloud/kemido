package com.kemido.sms.chinamobile.annotation;

import com.kemido.sms.chinamobile.condition.ChinaMobileSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 移动云短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ChinaMobileSmsEnabledCondition.class)
public @interface ConditionalOnChinaMobileSmsEnabled {
}
