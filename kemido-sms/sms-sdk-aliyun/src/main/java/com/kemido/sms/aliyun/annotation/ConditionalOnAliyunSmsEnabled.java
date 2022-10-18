package com.kemido.sms.aliyun.annotation;

import com.kemido.sms.aliyun.condition.AliyunSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 阿里云短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(AliyunSmsEnabledCondition.class)
public @interface ConditionalOnAliyunSmsEnabled {
}
