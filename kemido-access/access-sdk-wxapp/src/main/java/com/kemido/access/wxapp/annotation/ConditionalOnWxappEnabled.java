package com.kemido.access.wxapp.annotation;

import com.kemido.access.wxapp.condition.WxappEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 微信小程序开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(WxappEnabledCondition.class)
public @interface ConditionalOnWxappEnabled {
}
