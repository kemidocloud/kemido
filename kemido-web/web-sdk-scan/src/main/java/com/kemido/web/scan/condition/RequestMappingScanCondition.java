package com.kemido.web.scan.condition;

import com.kemido.web.core.support.WebPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Request Mapping 扫描条件 </p>
 */
public class RequestMappingScanCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(RequestMappingScanCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean result = WebPropertyFinder.isScanEnabled(conditionContext.getEnvironment());
        log.debug("[Kemido] |- Condition [Request Mapping Scan] value is [{}]", result);
        return result;
    }
}
