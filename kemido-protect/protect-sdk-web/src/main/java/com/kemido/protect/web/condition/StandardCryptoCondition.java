package com.kemido.protect.web.condition;

import com.kemido.protect.core.enums.CryptoStrategy;
import com.kemido.protect.core.support.CryptoPropertyFinder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 标准算法策略条件 </p>
 */
public class StandardCryptoCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(StandardCryptoCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = CryptoPropertyFinder.getCryptoStrategy(conditionContext.getEnvironment());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, CryptoStrategy.STANDARD.name());
        log.debug("[Kemido] |- Condition [Standard Crypto] value is [{}]", result);
        return result;
    }
}
