package com.kemido.data.jpa.hibernate.cache.spi;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.assistant.core.thread.TenantContextHolder;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.cache.spi.support.DomainDataStorageAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

/**
 * <p>Description: 自定义Hibernate二级缓存DomainDataStorageAccess </p>
 */
public class KemidoDomainDataStorageAccess implements DomainDataStorageAccess {

    private static final Logger log = LoggerFactory.getLogger(KemidoDomainDataStorageAccess.class);

    private Cache cache;

    public KemidoDomainDataStorageAccess() {
    }

    public KemidoDomainDataStorageAccess(Cache cache) {
        this.cache = cache;
    }

    private String secure(Object key) {
        String original = String.valueOf(key);
        if (StringUtils.isNotBlank(original) && StringUtils.startsWith(original, "sql:")) {
            String recent = SecureUtil.md5(original);
            log.trace("[Kemido] |- SPI - Secure the sql type key [{}] to [{}]", original, recent);
            return recent;
        }
        return original;
    }

    private String getTenantId() {
        String tenantId = TenantContextHolder.getTenantId();
        String result = StringUtils.isNotBlank(tenantId) ? tenantId : BaseConstants.DEFAULT_TENANT_ID;
        log.trace("[Kemido] |- SPI - Tenant identifier for jpa second level cache is : [{}]", result);
        return StringUtils.toRootLowerCase(result);
    }

    private String wrapper(Object key) {
        String original = secure(key);
        String tenantId = getTenantId();

        String result = tenantId + SymbolConstants.COLON + original;
        log.trace("[Kemido] |- SPI - Current cache key is : [{}]", result);
        return result;
    }

    private Object get(Object key) {
        Cache.ValueWrapper value = cache.get(key);

        if (ObjectUtils.isNotEmpty(value)) {
            return value.get();
        }
        return null;
    }

    @Override
    public boolean contains(Object key) {
        String wrapperKey = wrapper(key);
        Object value = this.get(wrapperKey);
        log.trace("[Kemido] |- SPI - check is key : [{}] exist.", wrapperKey);
        return ObjectUtils.isNotEmpty(value);
    }

    @Override
    public Object getFromCache(Object key, SharedSessionContractImplementor session) {
        String wrapperKey = wrapper(key);
        Object value = this.get(wrapperKey);
        log.trace("[Kemido] |- SPI - get from cache key is : [{}], value is : [{}]", wrapperKey, value);
        return value;
    }

    @Override
    public void putIntoCache(Object key, Object value, SharedSessionContractImplementor session) {
        String wrapperKey = wrapper(key);
        log.trace("[Kemido] |- SPI - put into cache key is : [{}], value is : [{}]", wrapperKey, value);
        cache.put(wrapperKey, value);
    }

    @Override
    public void removeFromCache(Object key, SharedSessionContractImplementor session) {
        String wrapperKey = wrapper(key);
        log.trace("[Kemido] |- SPI - remove from cache key is : [{}]", wrapperKey);
        cache.evict(wrapperKey);
    }

    @Override
    public void evictData(Object key) {
        String wrapperKey = wrapper(key);
        log.trace("[Kemido] |- SPI - evict key : [{}] from cache.", wrapperKey);
        cache.evict(wrapperKey);
    }

    @Override
    public void clearCache(SharedSessionContractImplementor session) {
        this.evictData();
    }

    @Override
    public void evictData() {
        log.trace("[Kemido] |- SPI - clear all cache data.");
        cache.clear();
    }

    @Override
    public void release() {
        log.trace("[Kemido] |- SPI - cache release.");
        cache.invalidate();
    }
}
