package com.kemido.oauth2.authorization.customizer;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.oauth2.core.definition.domain.KemidoUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: TokenCustomizer 通用处理 </p>
 */
public abstract class AbstractTokenCustomizer {

    protected void appendAll(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {

        appendAuthorities(attributes, authentication);
        appendCommons(attributes, authentication, authorizedScopes);

    }

    protected void appendAuthorities(Map<String, Object> attributes, Authentication authentication) {
        
        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            Set<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            attributes.put(BaseConstants.AUTHORITIES, authorities);
        }
    }

    protected void appendCommons(Map<String, Object> attributes, Authentication authentication, Set<String> authorizedScopes) {

        if (CollectionUtils.isNotEmpty(authorizedScopes)) {
            attributes.put(OAuth2ParameterNames.SCOPE, authorizedScopes);
        }

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            KemidoUser principal = (KemidoUser) authentication.getPrincipal();
            putUserInfo(attributes, principal);
        }

        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            Object details = authentication.getDetails();
            if (ObjectUtils.isNotEmpty(details) && details instanceof KemidoUser) {
                KemidoUser principal = (KemidoUser) details;
                putUserInfo(attributes, principal);
            }
        }
    }
    
    private void putUserInfo(Map<String, Object> attributes, KemidoUser principal) {
        attributes.put(BaseConstants.OPEN_ID, principal.getUserId());
        attributes.put(BaseConstants.ROLES, principal.getRoles());
    }
}
