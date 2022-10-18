package com.kemido.oauth2.metadata.storage;

import com.kemido.cache.jetcache.utils.JetCacheUtils;
import com.kemido.oauth2.metadata.constants.SecurityMetadataConstants;
import com.kemido.oauth2.metadata.matcher.KemidoRequestMatcher;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>Description: SecurityMetadata 本地存储 </p>
 */
@Component
public class SecurityMetadataLocalStorage {

    private static final Logger log = LoggerFactory.getLogger(SecurityMetadataLocalStorage.class);

    /**
     * 全部 ConfigAttributes 缓存。独立一个缓存，方便获取，减少重复读取。
     * 主要由 {@link FilterInvocationSecurityMetadataSource#getAllConfigAttributes()} 使用
     */
    private final Cache<String, Collection<ConfigAttribute>> allConfigAttributes;

    /**
     * attribute 索引辅助缓存，用于帮助 allConfigAttributes去重
     */
    private final Cache<String, Set<String>> indexes;

    /**
     * 模式匹配权限缓存。主要存储 包含 "*"、"?" 和 "{"、"}" 等特殊字符的路径权限。
     * 该种权限，需要通过遍历，利用 AntPathRequestMatcher 机制进行匹配
     */
    private final Cache<String, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> compatible;

    /**
     * 直接索引权限缓存，主要存储全路径权限
     * 该种权限，直接通过 Map Key 进行获取
     */
    private final Cache<KemidoRequestMatcher, Collection<ConfigAttribute>> indexable;

    public SecurityMetadataLocalStorage() {
        this.allConfigAttributes = JetCacheUtils.create(SecurityMetadataConstants.CACHE_NAME_SECURITY_METADATA_ATTRIBUTES, CacheType.LOCAL);
        this.indexes = JetCacheUtils.create(SecurityMetadataConstants.CACHE_NAME_SECURITY_METADATA_INDEXES, CacheType.LOCAL);
        this.compatible = JetCacheUtils.create(SecurityMetadataConstants.CACHE_NAME_SECURITY_METADATA_COMPATIBLE, CacheType.LOCAL);
        this.indexable = JetCacheUtils.create(SecurityMetadataConstants.CACHE_NAME_SECURITY_METADATA_INDEXABLE, CacheType.LOCAL);
    }

    private static final String KEY_ALL_CONFIG_ATTRIBUTES = "ALL_CONFIG_ATTRIBUTES";
    private static final String KEY_COMPATIBLE = "COMPATIBLE";
    private static final String KEY_INDEXES = "indexes";

    /**
     * 从 allConfigAttributes 缓存中读取数据
     *
     * @return @return 返回全部的权限
     */
    private Collection<ConfigAttribute> readFromAllConfigAttributes() {
        Collection<ConfigAttribute> configAttributes = this.allConfigAttributes.get(KEY_ALL_CONFIG_ATTRIBUTES);
        if (CollectionUtils.isNotEmpty(configAttributes)) {
            return configAttributes;
        }
        return new LinkedHashSet<>();
    }

    /**
     * 写入 allConfigAttributes 缓存
     *
     * @param configAttributes 权限配置属性对象集合
     */
    private void writeToAllConfigAttributes(Collection<ConfigAttribute> configAttributes) {
        this.allConfigAttributes.put(KEY_ALL_CONFIG_ATTRIBUTES, configAttributes);
    }

    /**
     * 从 indexes 缓存中读取数据
     *
     * @return @return 返回全部的权限
     */
    private Set<String> readFromIndexes() {
        Set<String> indexes = this.indexes.get(KEY_INDEXES);
        if (CollectionUtils.isNotEmpty(indexes)) {
            return indexes;
        }
        return new LinkedHashSet<>();
    }

    /**
     * 写入 indexes 缓存
     *
     * @param indexes 权限配置属性对象集合
     */
    private void writeToIndexes(Set<String> indexes) {
        this.indexes.put(KEY_INDEXES, indexes);
    }

    /**
     * 从 compatible 缓存中读取数据。
     *
     * @return 需要进行模式匹配的权限数据
     */
    private LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> readFromCompatible() {
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> compatible = this.compatible.get(KEY_COMPATIBLE);
        if (MapUtils.isNotEmpty(compatible)) {
            return compatible;
        }
        return new LinkedHashMap<>();

    }

    /**
     * 写入 compatible 缓存
     *
     * @param compatible 请求路径和权限配置属性映射Map
     */
    private void writeToCompatible(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> compatible) {
        this.compatible.put(KEY_COMPATIBLE, compatible);
    }

    /**
     * 从 indexable 缓存中读取数据
     *
     * @param kemidoRequestMatcher 自定义扩展的 AntPathRequestMatchers {@link KemidoRequestMatcher}
     * @return 权限配置属性对象集合
     */
    private Collection<ConfigAttribute> readFromIndexable(KemidoRequestMatcher kemidoRequestMatcher) {
        Collection<ConfigAttribute> indexable = this.indexable.get(kemidoRequestMatcher);
        if (CollectionUtils.isNotEmpty(indexable)) {
            return indexable;
        }
        return new LinkedHashSet<>();
    }

    /**
     * 写入 indexable 缓存
     *
     * @param kemidoRequestMatcher 自定义扩展的 AntPathRequestMatchers {@link KemidoRequestMatcher}
     * @param configAttributes        权限配置属性对象集合
     */
    private void writeToIndexable(KemidoRequestMatcher kemidoRequestMatcher, Collection<ConfigAttribute> configAttributes) {
        this.indexable.put(kemidoRequestMatcher, configAttributes);
    }

    /**
     * 从 allConfigAttributes 缓存中获取全部{@link ConfigAttribute}
     *
     * @return 缓存数据 {@link FilterInvocationSecurityMetadataSource#getAllConfigAttributes()}
     */
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return readFromAllConfigAttributes();
    }

    /**
     * 根据请求的 url 和 method 获取权限对象
     *
     * @param url    请求 URL
     * @param method 请求 method
     * @return 与请求url 和 method 匹配的权限数据，或者是空集合
     */
    public Collection<ConfigAttribute> getConfigAttribute(String url, String method) {
        KemidoRequestMatcher kemidoRequestMatcher = new KemidoRequestMatcher(url, method);
        return readFromIndexable(kemidoRequestMatcher);
    }

    /**
     * 将权限保存至 allConfigAttributes 缓存中
     *
     * @param securityMetadata {@link ConfigAttribute}
     */
    private void appendToAttributes(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> securityMetadata) {
        Collection<ConfigAttribute> allConfigAttributes = readFromAllConfigAttributes();
        Set<String> indexes = readFromIndexes();

        securityMetadata.forEach((key, value) -> value.forEach(attribute -> {
            String index = attribute.getAttribute();
            if (!indexes.contains(index)) {
                indexes.add(index);
                allConfigAttributes.add(attribute);
            }
        }));

        writeToIndexes(indexes);
        writeToAllConfigAttributes(allConfigAttributes);
    }

    /**
     * 从 compatible 缓存中获取全部不需要路径匹配的（包含*号的url）请求权限映射Map
     *
     * @return 如果缓存中存在，则返回请求权限映射Map集合，如果不存在则返回一个空的{@link LinkedHashMap}
     */
    public LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> getCompatible() {
        return readFromCompatible();
    }

    /**
     * 向 compatible 缓存中添加需要路径匹配的（包含*号的url）请求权限映射Map。
     * <p>
     * 如果缓存中不存在以{@link RequestMatcher}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link RequestMatcher}为Key的数据，那么合并数据
     *
     * @param kemidoRequestMatcher 请求匹配对象 {@link KemidoRequestMatcher}
     * @param configAttributes        权限配置 {@link ConfigAttribute}
     */
    private void appendToCompatible(KemidoRequestMatcher kemidoRequestMatcher, Collection<ConfigAttribute> configAttributes) {
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> compatible = this.getCompatible();
//        compatible.merge(requestMatcher, configAttributes, (oldConfigAttributes, newConfigAttributes) -> {
//            newConfigAttributes.addAll(oldConfigAttributes);
//            return newConfigAttributes;
//        });

        // 使用merge会让整个功能的设计更加复杂，暂时改为直接覆盖已有数据，后续视情况再做变更。
        compatible.put(kemidoRequestMatcher, configAttributes);
        log.trace("[Kemido] |- Append [{}] to Compatible cache, current size is [{}]", kemidoRequestMatcher, compatible.size());
        writeToCompatible(compatible);
    }

    /**
     * 向 compatible 缓存中添加需要路径匹配的（包含*号的url）请求权限映射Map。
     * <p>
     * 如果缓存中不存在以{@link RequestMatcher}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link RequestMatcher}为Key的数据，那么合并数据
     *
     * @param securityMetadata 请求权限映射Map
     */
    private void appendToCompatible(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> securityMetadata) {
        securityMetadata.forEach(this::appendToCompatible);
    }

    /**
     * 向 indexable 缓存中添加需要路径匹配的（包含*号的url）请求权限映射Map。
     * <p>
     * 如果缓存中不存在以{@link KemidoRequestMatcher}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link KemidoRequestMatcher}为Key的数据，那么合并数据
     *
     * @param kemidoRequestMatcher 请求匹配对象 {@link KemidoRequestMatcher}
     * @param configAttributes        权限配置 {@link ConfigAttribute}
     */
    private void appendToIndexable(KemidoRequestMatcher kemidoRequestMatcher, Collection<ConfigAttribute> configAttributes) {
        log.debug("[Kemido] |- Append [{}] to Indexable cache, current size is [{}]", kemidoRequestMatcher, configAttributes.size());
        writeToIndexable(kemidoRequestMatcher, configAttributes);
    }

    /**
     * 向 indexable 缓存中添加需要路径匹配的（不包含*号的url）请求权限映射Map。
     * <p>
     * 如果缓存中不存在以{@link RequestMatcher}为Key的数据，那么添加数据
     * 如果缓存中存在以{@link RequestMatcher}为Key的数据，那么合并数据
     *
     * @param securityMetadata 请求权限映射Map
     */
    private void appendToIndexable(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> securityMetadata) {
        securityMetadata.forEach(this::appendToIndexable);
    }

    /**
     * 将权限数据添加至本地存储
     *
     * @param securityMetadata 权限数据
     * @param isIndexable      true 存入 indexable cache；false 存入 compatible cache
     */
    public void addToStorage(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> securityMetadata, boolean isIndexable) {
        if (MapUtils.isNotEmpty(securityMetadata)) {
            if (isIndexable) {
                appendToIndexable(securityMetadata);
                appendToAttributes(securityMetadata);
            } else {
                appendToCompatible(securityMetadata);
                appendToAttributes(securityMetadata);
            }
        }
    }


    /**
     * 将权限数据添加至本地存储，存储之前进行规则冲突校验
     *
     * @param matchers         校验资源
     * @param securityMetadata 权限数据
     * @param isIndexable      true 存入 indexable cache；false 存入 compatible cache
     */
    public void addToStorage(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> matchers, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> securityMetadata, boolean isIndexable) {
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> result = new LinkedHashMap<>();
        if (MapUtils.isNotEmpty(matchers) && MapUtils.isNotEmpty(securityMetadata)){
            result = checkConflict(matchers, securityMetadata);
        }

        addToStorage(result, isIndexable);
    }

    /**
     * 规则冲突校验
     * <p>
     * 如存在规则冲突，则保留可支持最大化范围规则，冲突的其它规则则不保存
     *
     * @param matchers         校验资源
     * @param securityMetadata 权限数据
     * @return 去除冲突的权限数据
     */
    private LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> checkConflict(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> matchers, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> securityMetadata) {

        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> result = new LinkedHashMap<>(securityMetadata);

        for (KemidoRequestMatcher matcher : matchers.keySet()) {
            for (KemidoRequestMatcher item : securityMetadata.keySet()) {
                if (matcher.matches(item)) {
                    result.remove(item);
                    log.debug("[Kemido] |- Pattern [{}] is conflict with [{}], so remove it.", item.getPattern(), matcher.getPattern());
                }
            }
        }

        return result;
    }
}
