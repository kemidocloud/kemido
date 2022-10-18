package com.kemido.sms.netease.condition;

import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.sms.core.constants.SmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 网易短信开启条件 </p>
 */
public class NeteaseSmsEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(NeteaseSmsEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, SmsConstants.ITEM_NETEASE_ENABLED);
        log.debug("[Kemido] |- Condition [Netease Sms Enabled] value is [{}]", result);
        return result;
    }
}
