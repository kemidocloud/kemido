package com.kemido.oauth2.core.processor;

import com.kemido.oauth2.core.properties.SecurityProperties;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 安全过滤配置处理器 </p>
 * <p>
 * 对静态资源、开放接口等静态配置进行处理。整合默认配置和配置文件中的配置
 */
@Component
public class KemidoSecurityConfigureHandler {

    private static final List<String> DEFAULT_IGNORED_STATIC_RESOURCES = Lists.newArrayList(
            "/error/**",
            "/plugins/**",
            "/authorization/**",
            "/static/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/openapi.json",
            "/favicon.ico");
    private static final List<String> DEFAULT_PERMIT_ALL_RESOURCES = Lists.newArrayList("/open/**", "/oauth2/sign-out");

    private List<String> staticResources;
    private List<String> permitAllResources;

    private final SecurityProperties securityProperties;

    public KemidoSecurityConfigureHandler(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        this.staticResources = new ArrayList<>();
        this.permitAllResources = new ArrayList<>();
    }


    public List<String> getStaticResourceList() {
        if (CollectionUtils.isEmpty(this.staticResources)) {
            this.staticResources = merge(securityProperties.getMatcher().getStaticResources(), DEFAULT_IGNORED_STATIC_RESOURCES);
        }
        return this.staticResources;
    }

    public List<String> getPermitAllList() {
        if (CollectionUtils.isEmpty(this.permitAllResources)) {
            this.permitAllResources = merge(securityProperties.getMatcher().getPermitAll(), DEFAULT_PERMIT_ALL_RESOURCES);
        }
        return this.permitAllResources;
    }

    public String[] getStaticResourceArray() {
        return convertToArray(getStaticResourceList());
    }

    public String[] getPermitAllArray() {
        return convertToArray(getPermitAllList());
    }


    /**
     * 合并默认配置和自定义配置
     *
     * @param customResources  自定义配置
     * @param defaultResources 默认配置
     * @return 合并后的配置
     */
    private List<String> merge(List<String> customResources, List<String> defaultResources) {
        if (CollectionUtils.isEmpty(customResources)) {
            return defaultResources;
        } else {
            return CollectionUtils.collate(customResources, defaultResources);
        }
    }

    /**
     * 将 List 转换为 String[]
     *
     * @param resources List
     * @return String[]
     */
    private String[] convertToArray(List<String> resources) {
        if (CollectionUtils.isNotEmpty(resources)) {
            String[] result = new String[resources.size()];
            return resources.toArray(result);
        } else {
            return new String[]{};
        }

    }
}
