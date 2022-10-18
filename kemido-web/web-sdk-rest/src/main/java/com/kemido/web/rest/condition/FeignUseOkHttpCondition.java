package com.kemido.web.rest.condition;

import com.kemido.web.core.support.WebPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Feign 使用 OkHttp 客户端条件 </p>
 */
public class FeignUseOkHttpCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(FeignUseOkHttpCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean result = WebPropertyFinder.isFeignOkHttpEnabled(conditionContext.getEnvironment());
        log.debug("[Kemido] |- Condition [Feign Use OkHttp] value is [{}]", result);
        return result;
    }
}
