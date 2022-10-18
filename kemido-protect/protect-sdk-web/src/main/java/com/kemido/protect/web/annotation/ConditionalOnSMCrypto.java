package com.kemido.protect.web.annotation;

import com.kemido.protect.web.condition.SMCryptoCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 国密算法条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SMCryptoCondition.class)
public @interface ConditionalOnSMCrypto {
}
