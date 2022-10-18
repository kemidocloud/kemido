package com.kemido.oauth2.server.authorization.repository;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.oauth2.server.authorization.entity.OAuth2Scope;

/**
 * <p> Description : OauthScopeRepository </p>
 */
public interface OAuth2ScopeRepository extends BaseRepository<OAuth2Scope, String> {

    /**
     * 根据范围代码查询应用范围
     *
     * @param scopeCode 范围代码
     * @return 应用范围 {@link OAuth2Scope}
     */
    OAuth2Scope findByScopeCode(String scopeCode);

}
