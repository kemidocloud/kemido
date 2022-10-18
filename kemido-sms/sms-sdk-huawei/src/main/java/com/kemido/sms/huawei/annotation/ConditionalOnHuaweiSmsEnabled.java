package com.kemido.sms.huawei.annotation;

import com.kemido.sms.huawei.condition.HuaweiSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 华为云短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(HuaweiSmsEnabledCondition.class)
public @interface ConditionalOnHuaweiSmsEnabled {
}
