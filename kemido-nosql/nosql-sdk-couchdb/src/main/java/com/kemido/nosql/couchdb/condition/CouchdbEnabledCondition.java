package com.kemido.nosql.couchdb.condition;

import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.nosql.core.constants.NosqlConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Couchdb 注入开启条件 </p>
 */
public class CouchdbEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(CouchdbEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String url = PropertyResolver.getProperty(conditionContext, NosqlConstants.ITEM_COUCHDB_ENDPOINT);
        String username = PropertyResolver.getProperty(conditionContext, NosqlConstants.ITEM_COUCHDB_USERNAME);
        String password = PropertyResolver.getProperty(conditionContext, NosqlConstants.ITEM_COUCHDB_PASSWORD);
        boolean result = StringUtils.isNotBlank(url) && StringUtils.startsWith(url, "http") && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password);
        log.debug("[Kemido] |- Condition [Influxdb Enabled] value is [{}]", result);
        return result;
    }
}
