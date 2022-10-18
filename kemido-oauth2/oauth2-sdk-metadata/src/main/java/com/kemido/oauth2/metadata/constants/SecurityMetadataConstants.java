package com.kemido.oauth2.metadata.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: Security 模块 常量 </p>
 */
public interface SecurityMetadataConstants extends BaseConstants {

    String CACHE_SECURITY_PREFIX = CACHE_PREFIX + "security:";
    String CACHE_SECURITY_METADATA_PREFIX = CACHE_SECURITY_PREFIX + "metadata:";

    String CACHE_NAME_SECURITY_METADATA_ATTRIBUTES = CACHE_SECURITY_METADATA_PREFIX + "attributes:";
    String CACHE_NAME_SECURITY_METADATA_INDEXES = CACHE_SECURITY_METADATA_PREFIX + "indexes:";
    String CACHE_NAME_SECURITY_METADATA_INDEXABLE = CACHE_SECURITY_METADATA_PREFIX + "indexable:";
    String CACHE_NAME_SECURITY_METADATA_COMPATIBLE = CACHE_SECURITY_METADATA_PREFIX + "compatible:";
}
