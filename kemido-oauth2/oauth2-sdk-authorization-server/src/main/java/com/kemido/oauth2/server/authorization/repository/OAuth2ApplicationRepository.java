package com.kemido.oauth2.server.authorization.repository;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.oauth2.server.authorization.entity.OAuth2Application;

/**
 * <p>Description: OAuth2ApplicationRepository </p>
 */
public interface OAuth2ApplicationRepository extends BaseRepository<OAuth2Application, String> {

    /**
     * 根据 Client ID 查询 OAuth2Application
     *
     * @param clientId OAuth2Application 中的 clientId
     * @return {@link OAuth2Application}
     */
    OAuth2Application findByClientId(String clientId);
}
