package com.kemido.oauth2.server.authorization.service;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.data.core.service.BaseLayeredService;
import com.kemido.oauth2.server.authorization.entity.OAuth2Authority;
import com.kemido.oauth2.server.authorization.entity.OAuth2Scope;
import com.kemido.oauth2.server.authorization.repository.OAuth2ScopeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p> Description : OauthScopeService </p>
 */
@Service
public class OAuth2ScopeService extends BaseLayeredService<OAuth2Scope, String> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2ScopeService.class);

    private final OAuth2ScopeRepository oauthScopesRepository;

    @Autowired
    public OAuth2ScopeService(OAuth2ScopeRepository oauthScopesRepository) {
        this.oauthScopesRepository = oauthScopesRepository;
    }

    @Override
    public BaseRepository<OAuth2Scope, String> getRepository() {
        return oauthScopesRepository;
    }

    public OAuth2Scope authorize(String scopeId, Set<OAuth2Authority> authorities) {

        OAuth2Scope oldScope = findById(scopeId);
        oldScope.setAuthorities(authorities);

        OAuth2Scope newScope = saveOrUpdate(oldScope);
        log.debug("[Kemido] |- OAuth2ScopeService assign.");
        return newScope;
    }

    public OAuth2Scope findByScopeCode(String scopeCode) {
        OAuth2Scope scope = oauthScopesRepository.findByScopeCode(scopeCode);
        log.debug("[Kemido] |- OAuth2ScopeService findByScopeCode.");
        return scope;
    }
}
