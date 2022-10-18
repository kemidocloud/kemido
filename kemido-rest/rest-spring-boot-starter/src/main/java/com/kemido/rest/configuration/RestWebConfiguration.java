package com.kemido.rest.configuration;

import com.kemido.protect.web.secure.interceptor.AccessLimitedInterceptor;
import com.kemido.protect.web.secure.interceptor.IdempotentInterceptor;
import com.kemido.protect.web.tenant.interceptor.MultiTenancyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * <p>Description: WebMvcAutoConfiguration </p>
 */
@Configuration(proxyBeanMethods = false)
public class RestWebConfiguration implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(RestWebConfiguration.class);

    private final IdempotentInterceptor idempotentInterceptor;
    private final AccessLimitedInterceptor accessLimitedInterceptor;
    private final MultiTenancyInterceptor multiTenancyInterceptor;

    @Autowired
    public RestWebConfiguration(IdempotentInterceptor idempotentInterceptor, AccessLimitedInterceptor accessLimitedInterceptor, MultiTenancyInterceptor multiTenancyInterceptor) {
        this.idempotentInterceptor = idempotentInterceptor;
        this.accessLimitedInterceptor = accessLimitedInterceptor;
        this.multiTenancyInterceptor = multiTenancyInterceptor;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- SDK [Rest Web] Auto Configure.");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitedInterceptor);
        registry.addInterceptor(idempotentInterceptor);
        registry.addInterceptor(multiTenancyInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
