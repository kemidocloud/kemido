package com.kemido.web.rest.condition;

import com.kemido.web.core.support.WebPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Feign 使用 HttpClient 客户端条件 </p>
 */
public class FeignUseHttpClientCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(FeignUseHttpClientCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean result = WebPropertyFinder.isFeignHttpClientEnabled(conditionContext.getEnvironment());
        log.debug("[Kemido] |- Condition [Feign Use HttpClient] value is [{}]", result);
        return result;
    }
}
