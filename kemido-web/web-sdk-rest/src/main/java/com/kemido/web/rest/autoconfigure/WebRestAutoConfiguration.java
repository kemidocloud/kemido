package com.kemido.web.rest.autoconfigure;

import com.kemido.assistant.core.annotation.ConditionalOnSwaggerEnabled;
import com.kemido.web.core.context.KemidoApplicationContext;
import com.kemido.web.core.definition.OpenApiServerResolver;
import com.kemido.web.core.properties.EndpointProperties;
import com.kemido.web.core.properties.PlatformProperties;
import com.kemido.web.rest.configuration.OpenApiConfiguration;
import com.kemido.web.rest.processor.DefaultOpenApiServerResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 服务信息配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({PlatformProperties.class, EndpointProperties.class})
public class WebRestAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WebRestAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Web Rest] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public KemidoApplicationContext kemidoApplicationContext(ApplicationContext applicationContext, PlatformProperties platformProperties, EndpointProperties endpointProperties, ServerProperties serverProperties) {
        KemidoApplicationContext contextHolder = new KemidoApplicationContext(applicationContext, platformProperties, endpointProperties, serverProperties);
        log.trace("[Kemido] |- Bean [Kemido Context Holder] Auto Configure.");
        return contextHolder;
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenApiServerResolver openApiServerResolver(KemidoApplicationContext kemidoApplicationContext) {
        DefaultOpenApiServerResolver defaultOpenApiServerResolver = new DefaultOpenApiServerResolver(kemidoApplicationContext);
        log.trace("[Kemido] |- Bean [Open Api Server Resolver] Auto Configure.");
        return defaultOpenApiServerResolver;
    }

    /**
     * 为了方便控制注入的顺序
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSwaggerEnabled
    @Import(OpenApiConfiguration.class)
    static class OpenApiInit {

    }
}
