package com.kemido.oauth2.data.jpa.repository;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.oauth2.data.jpa.entity.KemidoRegisteredClient;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Optional;

/**
 * <p>Description: KemidoRegisteredClientRepository </p>
 */
public interface KemidoRegisteredClientRepository extends BaseRepository<KemidoRegisteredClient, String> {

    /**
     * 根据 ClientId 查询 RegisteredClient
     *
     * @param clientId OAuth2 客户端ID
     * @return OAuth2 客户端配置
     */
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Optional<KemidoRegisteredClient> findByClientId(String clientId);
}
