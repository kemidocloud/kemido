package com.kemido.pay.wechat.annotation;

import com.kemido.pay.core.constants.PayConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * <p>Description: 微信支付启用条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(value = PayConstants.ITEM_WXPAY_ENABLED, havingValue = "true")
public @interface ConditionalOnWxpay {
}
