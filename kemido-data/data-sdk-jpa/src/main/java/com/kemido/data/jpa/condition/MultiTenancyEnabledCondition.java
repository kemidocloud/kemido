package com.kemido.data.jpa.condition;

import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.data.core.constants.DataConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Couchdb 注入开启条件 </p>
 */
public class MultiTenancyEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(MultiTenancyEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, DataConstants.ITEM_MULTI_TENANCY_ENABLED);
        log.debug("[Kemido] |- Condition [Multi Tenancy Enabled] value is [{}]", result);
        return result;
    }
}
