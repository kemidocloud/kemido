package com.kemido.pay.all.annotation;

import com.kemido.pay.all.configuration.PayConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启支付支持注解 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PayConfiguration.class)
public @interface EnableKemidoPay {
}
