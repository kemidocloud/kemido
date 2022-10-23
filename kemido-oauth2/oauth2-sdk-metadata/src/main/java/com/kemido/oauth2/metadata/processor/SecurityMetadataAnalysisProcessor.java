package com.kemido.oauth2.metadata.processor;

import com.kemido.oauth2.core.definition.domain.SecurityAttribute;
import com.kemido.oauth2.metadata.enums.Category;
import com.kemido.oauth2.metadata.matcher.KemidoRequestMatcher;
import com.kemido.oauth2.metadata.storage.SecurityMetadataLocalStorage;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Description: SecurityMetadata异步处理Service </p>
 */
@Service
public class SecurityMetadataAnalysisProcessor {

    private static final Logger log = LoggerFactory.getLogger(SecurityMetadataAnalysisProcessor.class);

    private final SecurityMetadataLocalStorage securityMetadataLocalStorage;
    private final ExpressionSecurityMetadataParser expressionSecurityMetadataParser;

    public SecurityMetadataAnalysisProcessor(SecurityMetadataLocalStorage securityMetadataLocalStorage, ExpressionSecurityMetadataParser expressionSecurityMetadataParser) {
        this.securityMetadataLocalStorage = securityMetadataLocalStorage;
        this.expressionSecurityMetadataParser = expressionSecurityMetadataParser;
    }

    /**
     * 各个服务静态化配置的权限过滤，通常为通配符型或者全路径型，很少有站位符型。即：大多数情况为 {@code Category.WILDCARD} 和 {@code Category.PLACEHOLDER}，很少有 {@code Category.FULL_PATH}
     * <p>
     * 此处的逻辑是：
     * 1. 先处理各个服务静态化配置的权限，当前假设不会有{@code Category.FULL_PATH}类型的权限。后期如果该种权限较多再补充即可。
     * 同时，静态服务都是开发人员手工配置，假定手工配置时就会对是否冲突进行处理，当然也可能出现冲突，那么这个开发人员得多不负责。
     * 2. 经过考虑，服务本地接口扫描完，就对所有的 RequestMapping 做一遍解析，现在感觉意义不大。
     * 因为，RequestMapping 汇总至 UPMS 后，还会做一次统一的分发。所以当前的设计思路是不对 RequestMapping 进行处理。后续根据需要再补充即可。
     */
    public void processSecurityRequestMapping() {

        log.debug("[Kemido] |- [3] Process local configured security metadata.");

        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> requestMappings = expressionSecurityMetadataParser.getConfiguredSecurityMetadata();
        if (MapUtils.isNotEmpty(requestMappings)) {
            Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> grouping = groupSecurityRequestMap(requestMappings);

            LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> wildcards = grouping.get(Category.WILDCARD);
            securityMetadataLocalStorage.addToStorage(wildcards, false);

            LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> fullPaths = grouping.get(Category.FULL_PATH);
            securityMetadataLocalStorage.addToStorage(fullPaths, true);
        }
    }

    /**
     * 处理 UPMS 分发的 SecurityAttributes，将其转换、解析为表达式权限，并存入本地缓存，用于权限校验
     * <p>
     * 处理过程中，会根据规则对权限类型分组，然后进行去重的操作。
     *
     * @param securityAttributes 权限数据
     */
    public void processSecurityMetadata(List<SecurityAttribute> securityAttributes) {

        // 从缓存中获取全部带有特殊字符的匹配规则
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> compatibles = securityMetadataLocalStorage.getCompatible();
        // 创建一个临时的 Matcher 容器
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> matchers = new LinkedHashMap<>(compatibles);

        // 对分发的 SecurityAttributes 进行分组
        Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> grouping = groupingSecurityAttributes(securityAttributes);

        // 拿到带有通配符的分组数据
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> wildcards = grouping.get(Category.WILDCARD);
        if (MapUtils.isNotEmpty(wildcards)) {
            matchers.putAll(wildcards);
        }

        // 拿到带有占位符的分组数据，并检测是否存在冲突的匹配规则，然后将结果存入本地存储
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> placeholders = grouping.get(Category.PLACEHOLDER);
        log.debug("[Kemido] |- Store placeholder type security attributes.");
        securityMetadataLocalStorage.addToStorage(matchers, placeholders, false);

        // 拿到全路径的分组数据，并检测是否存在冲突的匹配规则，然后将结果存入本地存储
        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> fullPaths = grouping.get(Category.FULL_PATH);
        log.debug("[Kemido] |- Store full path type security attributes.");
        securityMetadataLocalStorage.addToStorage(matchers, fullPaths, true);

        log.debug("[Kemido] |- [8] Security attributes process has FINISHED!");
    }

    /**
     * 将 UPMS 分发的 SecurityAttributes 数据进行权限转换并分组
     *
     * @param securityAttributes 权限数据
     * @return 分组后的权限数据
     */
    private Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> groupingSecurityAttributes(List<SecurityAttribute> securityAttributes) {

        Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> group = new LinkedHashMap<>();

        securityAttributes.parallelStream().forEach(securityAttribute -> {
            LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> resources = expressionSecurityMetadataParser.postProcess(securityAttribute);
            appendToGroup(group, Category.getCategory(securityAttribute.getUrl()), resources);
        });

        log.debug("[Kemido] |- Grouping security attributes by category.");
        return group;
    }

    /**
     * 将各个服务配置的静态权限数据分组
     *
     * @param requestMap 静态权限数据
     * @return 分组后的权限数据
     */
    private Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> groupSecurityRequestMap(LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> requestMap) {

        Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> group = new LinkedHashMap<>();

        requestMap.forEach((key, value) -> {
            LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> resources = new LinkedHashMap<>();
            resources.put(key, value);
            appendToGroup(group, Category.getCategory(key.getPattern()), resources);
        });

        return group;
    }

    /**
     * 将解析后的数据添加对应的分组中
     *
     * @param container 分组结果数据容器
     * @param category  分组类别
     * @param resources 权限数据
     */
    private void appendToGroup(Map<Category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>>> container, Category category, LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> resources) {

        LinkedHashMap<KemidoRequestMatcher, Collection<ConfigAttribute>> value = new LinkedHashMap<>();

        if (container.containsKey(category)) {
            value = container.get(category);
        }

        value.putAll(resources);
        container.put(category, value);
    }
}
