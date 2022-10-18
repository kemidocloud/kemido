package com.kemido.sms.tencent.annotation;

import com.kemido.sms.tencent.condition.TencentSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 腾讯云短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(TencentSmsEnabledCondition.class)
public @interface ConditionalOnTencentSmsEnabled {
}
