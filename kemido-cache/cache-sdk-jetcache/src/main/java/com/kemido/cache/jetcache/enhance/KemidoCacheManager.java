package com.kemido.cache.jetcache.enhance;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.cache.core.properties.CacheProperties;
import com.kemido.cache.core.properties.Expire;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.util.Map;

/**
 * <p>Description: 自定义 缓存管理器 </p>
 */
public class KemidoCacheManager extends JetCacheSpringCacheManager {

    private static final Logger log = LoggerFactory.getLogger(KemidoCacheManager.class);

    private final CacheProperties cacheProperties;

    public KemidoCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
        super(jetCacheCreateCacheFactory);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public KemidoCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties, String... cacheNames) {
        super(jetCacheCreateCacheFactory, cacheNames);
        this.cacheProperties = cacheProperties;
    }

    @Override
    protected Cache createJetCache(String name) {
        Map<String, Expire> expires = cacheProperties.getExpires();
        if (MapUtils.isNotEmpty(expires)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if (expires.containsKey(key)) {
                Expire expire = expires.get(key);
                log.debug("[Kemido] |- CACHE - Cache [{}] is set to use CUSTOM expire.", name);
                return super.createJetCache(name, expire.getTtl());
            }
        }
        return super.createJetCache(name);
    }
}
