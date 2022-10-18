package com.kemido.oss.minio.condition;

import com.kemido.assistant.core.support.PropertyResolver;
import com.kemido.oss.core.constants.OssConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Minio是否开启条件 </p>
 */
public class MinioEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(MinioEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String endpoint = PropertyResolver.getProperty(conditionContext, OssConstants.ITEM_MINIO_ENDPOINT);
        String accessKey = PropertyResolver.getProperty(conditionContext, OssConstants.ITEM_MINIO_ACCESSKEY);
        String secretkey = PropertyResolver.getProperty(conditionContext, OssConstants.ITEM_MINIO_SECRETKEY);
        boolean result = StringUtils.isNotBlank(endpoint) && StringUtils.isNotBlank(accessKey) && StringUtils.isNotBlank(secretkey);
        log.debug("[Kemido] |- Condition [Minio Enabled] value is [{}]", result);
        return result;
    }
}
