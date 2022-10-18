package com.kemido.oauth2.server.authorization.processor;

import com.kemido.oauth2.core.definition.domain.KemidoGrantedAuthority;
import com.kemido.oauth2.core.definition.service.EnhanceClientDetailsService;
import com.kemido.oauth2.server.authorization.entity.OAuth2Application;
import com.kemido.oauth2.server.authorization.entity.OAuth2Authority;
import com.kemido.oauth2.server.authorization.entity.OAuth2Scope;
import com.kemido.oauth2.server.authorization.service.OAuth2ApplicationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: 客户端交互处理器 </p>
 */
public class KemidoClientDetailsService implements EnhanceClientDetailsService {

    private final OAuth2ApplicationService applicationService;

    public KemidoClientDetailsService(OAuth2ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public Set<KemidoGrantedAuthority> findAuthoritiesById(String clientId) {

        OAuth2Application application = applicationService.findByClientId(clientId);
        if (ObjectUtils.isNotEmpty(application)) {
            Set<OAuth2Scope> scopes = application.getScopes();
            Set<KemidoGrantedAuthority> result = new HashSet<>();
            if (CollectionUtils.isNotEmpty(scopes)) {
                for (OAuth2Scope scope : scopes) {
                    Set<OAuth2Authority> authorities = scope.getAuthorities();
                    if (CollectionUtils.isNotEmpty(authorities)) {
                        Set<KemidoGrantedAuthority> grantedAuthorities = authorities.stream().map(item -> new KemidoGrantedAuthority(item.getAuthorityCode())).collect(Collectors.toSet());
                        result.addAll(grantedAuthorities);
                    }
                }
            }
            return result;
        }

        return new HashSet<>();
    }
}
