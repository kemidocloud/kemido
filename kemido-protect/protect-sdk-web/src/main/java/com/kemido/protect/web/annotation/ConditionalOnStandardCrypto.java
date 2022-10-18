package com.kemido.protect.web.annotation;

import com.kemido.protect.web.condition.StandardCryptoCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 标准加密算法 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(StandardCryptoCondition.class)
public @interface ConditionalOnStandardCrypto {
}
