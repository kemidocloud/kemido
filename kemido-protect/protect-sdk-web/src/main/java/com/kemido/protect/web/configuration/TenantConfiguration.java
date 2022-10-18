package com.kemido.protect.web.configuration;

import com.kemido.protect.web.tenant.interceptor.MultiTenancyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 租户请求拦截配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class TenantConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TenantConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Protect Tenant] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public MultiTenancyInterceptor tenantInterceptor() {
        MultiTenancyInterceptor multiTenancyInterceptor = new MultiTenancyInterceptor();
        log.trace("[Kemido] |- Bean [Idempotent Interceptor] Auto Configure.");
        return multiTenancyInterceptor;
    }
}
