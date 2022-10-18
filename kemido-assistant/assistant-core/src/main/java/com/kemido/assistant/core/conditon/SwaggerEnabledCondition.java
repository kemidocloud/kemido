package com.kemido.assistant.core.conditon;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.support.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Swagger 开启条件 </p>
 */
public class SwaggerEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(SwaggerEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, BaseConstants.ITEM_SWAGGER_ENABLED);
        log.debug("[Kemido] |- Condition [Swagger Enabled] value is [{}]", result);
        return result;
    }
}
