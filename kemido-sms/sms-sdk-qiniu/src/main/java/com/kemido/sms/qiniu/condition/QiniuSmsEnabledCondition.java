package com.kemido.sms.qiniu.condition;

import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.sms.core.constants.SmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 七牛云短信开启条件 </p>
 */
public class QiniuSmsEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(QiniuSmsEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, SmsConstants.ITEM_QINIU_ENABLED);
        log.debug("[Kemido] |- Condition [Qiniu Sms Enabled] value is [{}]", result);
        return result;
    }
}
