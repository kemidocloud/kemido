package com.kemido.access.wxmpp.annotation;

import com.kemido.access.wxmpp.condition.WxmppEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 微信公众号开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(WxmppEnabledCondition.class)
public @interface ConditionalOnWxmppEnabled {

}
