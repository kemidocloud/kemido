package com.kemido.cache.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: Cache Property值常量 </p>
 */
public interface CacheConstants extends BaseConstants {

    /* ---------- Kemido 配置属性（第二层） ---------- */
    /**
     * platform
     */
    String PROPERTY_PREFIX_CACHE = PROPERTY_PREFIX_HERODOTUS + ".cache";

    /* ---------- Spring 相关基础配置属性（第一层） ---------- */

    String PROPERTY_REDIS_REDISSON = PROPERTY_SPRING_REDIS + ".redisson";

    String ITEM_REDISSON_ENABLED = PROPERTY_REDIS_REDISSON + PROPERTY_ENABLED;
}
