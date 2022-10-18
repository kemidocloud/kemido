package com.kemido.cache.jetcache.enhance;

import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;
import com.alicp.jetcache.Cache;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * <p>Description: 基于 JetCache 的 Spring Cache 扩展 </p>
 */
public class JetCacheSpringCache extends AbstractValueAdaptingCache {

    private static final Logger log = LoggerFactory.getLogger(JetCacheSpringCache.class);

    private final String cacheName;
    private final Cache<Object, Object> cache;

    public JetCacheSpringCache(String cacheName, Cache<Object, Object> cache, boolean allowNullValues) {
        super(allowNullValues);
        this.cacheName = cacheName;
        this.cache = cache;
    }

    @Override
    public String getName() {
        return this.cacheName;
    }

    @Override
    public final Cache<Object, Object> getNativeCache() {
        return this.cache;
    }

    @Override
    @Nullable
    protected Object lookup(Object key) {
        Object value = cache.get(key);
        if (ObjectUtils.isNotEmpty(value)) {
            log.trace("[Kemido] |- CACHE - Lookup data in kemido cache, value is : [{}]", JacksonUtils.toJson(value));
            return value;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T get(Object key, Callable<T> valueLoader) {

        log.trace("[Kemido] |- CACHE - Get data in kemido cache, key: {}", key);

        return (T) fromStoreValue(cache.computeIfAbsent(key, k -> {
            try {
                return toStoreValue(valueLoader.call());
            }
            catch (Throwable ex) {
                throw new ValueRetrievalException(key, valueLoader, ex);
            }
        }));
    }

    @Override
    @Nullable
    public void put(Object key, @Nullable Object value) {
        log.trace("[Kemido] |- CACHE - Put data in kemido cache, key: {}", key);
        cache.put(key, this.toStoreValue(value));
    }


    @Override
    @Nullable
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        log.trace("[Kemido] |- CACHE - PutIfPresent data in kemido cache, key: {}", key);
        Object existing = cache.putIfAbsent(key, toStoreValue(value));
        return toValueWrapper(existing);
    }

    @Override
    public void evict(Object key) {
        log.trace("[Kemido] |- CACHE - Evict data in kemido cache, key: {}", key);
        cache.remove(key);
    }

    @Override
    public boolean evictIfPresent(Object key) {
        log.trace("[Kemido] |- CACHE - EvictIfPresent data in kemido cache, key: {}", key);
        return cache.remove(key);
    }

    @Override
    public void clear() {
        log.trace("[Kemido] |- CACHE - Clear data in kemido cache.");
        cache.close();
    }
}
