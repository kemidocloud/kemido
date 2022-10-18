package com.kemido.oauth2.data.jpa.repository;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.oauth2.data.jpa.entity.KemidoAuthorizationConsent;
import com.kemido.oauth2.data.jpa.generator.KemidoAuthorizationConsentId;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Optional;

/**
 * <p>Description: KemidoAuthorizationConsentRepository </p>
 */
public interface KemidoAuthorizationConsentRepository extends BaseRepository<KemidoAuthorizationConsent, KemidoAuthorizationConsentId> {

    /**
     * 根据 client id 和 principalName 查询 OAuth2 确认信息
     *
     * @param registeredClientId 注册OAuth2客户端ID
     * @param principalName      用户名
     * @return OAuth2 认证确认信息 {@link KemidoAuthorizationConsent}
     */
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Optional<KemidoAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

    /**
     * 根据 client id 和 principalName 删除 OAuth2 确认信息
     *
     * @param registeredClientId 注册OAuth2客户端ID
     * @param principalName      用户名
     */
    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);
}
