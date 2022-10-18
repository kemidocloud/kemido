package com.kemido.cache.redis.condition;

import com.kemido.assistant.core.support.PropertyFinder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 开启基于 Redis 的 Session 共享条件 </p>
 */
public class RedisSessionSharingCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(RedisSessionSharingCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = PropertyFinder.getSessionStoreType(conditionContext.getEnvironment());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, "redis");
        log.debug("[Kemido] |- Condition [Redis Session Sharing] value is [{}]", result);
        return result;
    }
}
