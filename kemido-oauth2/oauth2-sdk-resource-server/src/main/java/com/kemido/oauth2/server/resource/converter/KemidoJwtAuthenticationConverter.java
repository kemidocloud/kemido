package com.kemido.oauth2.server.resource.converter;

import com.kemido.assistant.core.constants.BaseConstants;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * <p>Description: 扩展的 JwtAuthenticationConverter </p>
 */
public class KemidoJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public KemidoJwtAuthenticationConverter() {
        KemidoJwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new KemidoJwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName(BaseConstants.AUTHORITIES);

        this.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    }
}
