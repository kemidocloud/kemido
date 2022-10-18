package com.kemido.oauth2.data.jpa.repository;

import com.kemido.data.core.repository.BaseRepository;
import com.kemido.oauth2.data.jpa.entity.KemidoAuthorization;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>Description: KemidoAuthorizationRepository </p>
 */
public interface KemidoAuthorizationRepository extends BaseRepository<KemidoAuthorization, String> {

    /**
     * 根据 State 查询 OAuth2 认证信息
     *
     * @param state OAuth2 Authorization Code 模式参数 State
     * @return OAuth2 认证信息 {@link KemidoAuthorization}
     */
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Optional<KemidoAuthorization> findByState(String state);

    /**
     * 根据 authorizationCode 查询 OAuth2 认证信息
     *
     * @param authorizationCode OAuth2 Authorization Code 模式参数 code
     * @return OAuth2 认证信息 {@link KemidoAuthorization}
     */
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Optional<KemidoAuthorization> findByAuthorizationCode(String authorizationCode);

    /**
     * 根据 Access Token 查询 OAuth2 认证信息
     *
     * @param accessToken OAuth2 accessToken
     * @return OAuth2 认证信息 {@link KemidoAuthorization}
     */
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Optional<KemidoAuthorization> findByAccessToken(String accessToken);

    /**
     * 根据 Refresh Token 查询 OAuth2 认证信息
     *
     * @param refreshToken OAuth2 refreshToken
     * @return OAuth2 认证信息 {@link KemidoAuthorization}
     */
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
    Optional<KemidoAuthorization> findByRefreshToken(String refreshToken);

    /**
     * 根据客户端ID和用户名查询未过期Token
     *
     * @param registeredClientId 客户端ID
     * @param principalName      用户名称
     * @param localDateTime      时间
     * @return 认证信息列表
     */
    List<KemidoAuthorization> findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(String registeredClientId, String principalName, LocalDateTime localDateTime);

    /**
     * 根据 RefreshToken 过期时间，清理历史 Token信息
     * <p>
     * OAuth2Authorization 表中存在 AccessToken、OidcToken、RefreshToken 等三个过期时间。
     * 正常的删除逻辑应该是三个过期时间都已经过期才行。但是有特殊情况：
     * 1. OidcToken 的过期时间有可能为空，这就增加了 SQL 处理的复杂度。
     * 2. 逻辑上 RefreshToken 的过期应该是最长的(这是默认配置正确的情况)
     * 因此，目前就简单的根据 RefreshToken过期时间进行处理
     *
     * @param localDateTime 时间
     */
    void deleteByRefreshTokenExpiresAtBefore(LocalDateTime localDateTime);
}
