package com.kemido.oauth2.core.annotation;

import com.kemido.oauth2.core.condition.AutoUnlockUserAccountCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 自动解锁用户账号条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(AutoUnlockUserAccountCondition.class)
public @interface ConditionalOnAutoUnlockUserAccount {
}
