package com.kemido.oauth2.server.authorization.service;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.data.core.service.BaseLayeredService;
import com.kemido.oauth2.server.authorization.entity.OAuth2Authority;
import com.kemido.oauth2.server.authorization.repository.OAuth2AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2AuthorityService </p>
 */
@Service
public class OAuth2AuthorityService extends BaseLayeredService<OAuth2Authority, String> {

    private final OAuth2AuthorityRepository authorityRepository;

    @Autowired
    public OAuth2AuthorityService(OAuth2AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public BaseRepository<OAuth2Authority, String> getRepository() {
        return authorityRepository;
    }
}
