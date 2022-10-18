package com.kemido.cache.caffeine.enhance;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.cache.core.properties.CacheProperties;
import com.kemido.cache.core.properties.Expire;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Map;

/**
 * <p>Description: 扩展的 CaffeineCacheManager </p>
 * <p>
 * 用于支持 Caffeine 缓存可以针对实体进行单独的过期时间设定
 */
public class KemidoCaffeineCacheManager extends CaffeineCacheManager {

    private static final Logger log = LoggerFactory.getLogger(KemidoCaffeineCacheManager.class);

    private final CacheProperties cacheProperties;

    public KemidoCaffeineCacheManager(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public KemidoCaffeineCacheManager(CacheProperties cacheProperties, String... cacheNames) {
        super(cacheNames);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    @Override
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Map<String, Expire> expires = cacheProperties.getExpires();
        if (MapUtils.isNotEmpty(expires)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if(expires.containsKey(key)) {
                Expire expire = expires.get(key);
                log.debug("[Kemido] |- CACHE - Caffeine cache [{}] is setted to use CUSTEM exprie.", name);
                return Caffeine.newBuilder().expireAfterWrite(expire.getDuration(), expire.getUnit()).build();
            }
        }

        return super.createNativeCaffeineCache(name);
    }
}
