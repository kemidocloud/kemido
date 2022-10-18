package com.kemido.oauth2.server.resource.customizer;

import com.kemido.assistant.core.enums.Target;
import com.kemido.oauth2.core.properties.SecurityProperties;
import com.kemido.oauth2.core.response.KemidoAccessDeniedHandler;
import com.kemido.oauth2.core.response.KemidoAuthenticationEntryPoint;
import com.kemido.oauth2.server.resource.converter.KemidoJwtAuthenticationConverter;
import com.kemido.oauth2.server.resource.introspector.KemidoOpaqueTokenIntrospector;
import com.kemido.web.core.properties.EndpointProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.util.Assert;

/**
 * <p>Description: Token 配置 通用代码 </p>
 */
public class KemidoStrategyTokenConfigurer {

    public static Builder from(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        return new Builder(configurer);
    }

    public static class Builder {

        private JwtDecoder jwtDecoder;
        private SecurityProperties securityProperties;
        private OAuth2ResourceServerProperties resourceServerProperties;
        private EndpointProperties endpointProperties;

        private final OAuth2ResourceServerConfigurer<HttpSecurity> configurer;

        public Builder(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
            this.configurer = configurer;
        }

        public Builder jwtDecoder(JwtDecoder jwtDecoder) {
            this.jwtDecoder = jwtDecoder;
            return this;
        }

        public Builder securityProperties(SecurityProperties securityProperties) {
            this.securityProperties = securityProperties;
            return this;
        }

        public Builder resourceServerProperties(OAuth2ResourceServerProperties resourceServerProperties) {
            this.resourceServerProperties = resourceServerProperties;
            return this;
        }

        public Builder endpointProperties(EndpointProperties endpointProperties) {
            this.endpointProperties = endpointProperties;
            return this;
        }

        public OAuth2ResourceServerConfigurer<HttpSecurity> build() {
            Assert.notNull(this.jwtDecoder, "jwtDecoder must be set");
            Assert.notNull(this.securityProperties, "securityProperties must be set");
            Assert.notNull(this.resourceServerProperties, "resourceServerProperties must be set");
            Assert.notNull(this.endpointProperties, "endpointProperties must be set");

            if (this.securityProperties.getValidate() == Target.REMOTE) {
                this.configurer
                        .opaqueToken(opaque -> opaque.introspector(new KemidoOpaqueTokenIntrospector(this.endpointProperties, this.resourceServerProperties)))
                        .accessDeniedHandler(new KemidoAccessDeniedHandler())
                        .authenticationEntryPoint(new KemidoAuthenticationEntryPoint());
            } else {
                this.configurer
                        .jwt(jwt -> jwt.decoder(this.jwtDecoder).jwtAuthenticationConverter(new KemidoJwtAuthenticationConverter()))
                        .bearerTokenResolver(new DefaultBearerTokenResolver())
                        .accessDeniedHandler(new KemidoAccessDeniedHandler())
                        .authenticationEntryPoint(new KemidoAuthenticationEntryPoint());
            }
            return this.configurer;
        }
    }
}
