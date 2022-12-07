package com.kemido.web.rest.configuration;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.web.core.definition.OpenApiServerResolver;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p> Description : SwaggerConfiguration </p>
 * <p>
 * 原来的@EnableSwagger2去掉
 */
@Configuration(proxyBeanMethods = false)
@SecuritySchemes({
        @SecurityScheme(name = BaseConstants.OPEN_API_SECURITY_SCHEME_BEARER_NAME, type = SecuritySchemeType.OAUTH2, bearerFormat = "JWT", scheme = "bearer",
                flows = @OAuthFlows(
                        password = @OAuthFlow(authorizationUrl = "${kemido.endpoint.authorization-uri}", tokenUrl = "${kemido.endpoint.access-token-uri}", refreshUrl = "${kemido.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all")),
                        clientCredentials = @OAuthFlow(authorizationUrl = "${kemido.endpoint.authorization-uri}", tokenUrl = "${kemido.endpoint.access-token-uri}", refreshUrl = "${kemido.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all"))
//                        authorizationCode = @OAuthFlow(authorizationUrl = "${kemido.platform.endpoint.user-authorization-uri}", tokenUrl = "${kemido.platform.endpoint.access-token-uri}", refreshUrl = "${kemido.platform.endpoint.access-token-uri}", scopes = @OAuthScope(name = "all"))
                )),
})
public class OpenApiConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OpenApiConfiguration.class);

    /**
     * Knife4j的一个问题，只能设置"oauth2"，否则token配置界面不会显示
     */
    private static final String SCHEMA_OAUTH_NAME = "oauth2";

    private final OpenApiServerResolver openApiServerResolver;

    @Autowired
    public OpenApiConfiguration(OpenApiServerResolver openApiServerResolver) {
        this.openApiServerResolver = openApiServerResolver;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Web Rest Swagger] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI createOpenApi() {
        return new OpenAPI()
                .servers(openApiServerResolver.getServers())
                .info(new Info().title("Kemido Cloud")
                        .description("Kemido Cloud Microservices Architecture")
                        .version("Swagger V3")
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/")))
                .externalDocs(new ExternalDocumentation()
                        .description("Kemido Cloud Documentation")
                        .url(" https://www.kemido.cn"));
    }
}
