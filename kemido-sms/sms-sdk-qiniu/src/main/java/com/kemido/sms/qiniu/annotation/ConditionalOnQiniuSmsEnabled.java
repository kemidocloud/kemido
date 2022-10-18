package com.kemido.sms.qiniu.annotation;

import com.kemido.sms.qiniu.condition.QiniuSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 七牛云短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(QiniuSmsEnabledCondition.class)
public @interface ConditionalOnQiniuSmsEnabled {
}
