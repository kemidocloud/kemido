package com.kemido.sms.yunpian.annotation;

import com.kemido.sms.yunpian.condition.YunpianSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 云片网短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(YunpianSmsEnabledCondition.class)
public @interface ConditionalOnYunpianSmsEnabled {
}
