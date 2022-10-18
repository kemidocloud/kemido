package com.kemido.sms.netease.annotation;

import com.kemido.sms.netease.condition.NeteaseSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 网易短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(NeteaseSmsEnabledCondition.class)
public @interface ConditionalOnNeteaseSmsEnabled {
}
