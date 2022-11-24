package com.kemido.oauth2.server.authorization.entity;

import com.kemido.data.core.entity.BaseSysEntity;
import com.kemido.oauth2.core.constants.OAuth2Constants;
import com.kemido.oauth2.core.enums.ApplicationType;
import com.kemido.oauth2.core.enums.Signature;
import com.kemido.oauth2.core.enums.TokenFormat;
import cn.hutool.core.util.IdUtil;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2 应用 </p>
 * <p>
 * Spring Authorization Server 默认的 RegisteredClient 不便于扩展。增加该类用于存储标准 RegisteredClient 表结构以外的扩展信息。
 */
@Entity
@Table(name = "oauth2_application", indexes = {
        @Index(name = "oauth2_application_id_idx", columnList = "application_id"),
        @Index(name = "oauth2_application_cid_idx", columnList = "client_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_APPLICATION)
public class OAuth2Application extends BaseSysEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "application_id", length = 64)
    private String applicationId;

    @Column(name = "application_name", length = 128)
    private String applicationName;

    @Column(name = "abbreviation", length = 64)
    private String abbreviation;

    @Column(name = "logo", length = 1024)
    private String logo;

    @Column(name = "homepage", length = 1024)
    private String homepage;

    @Column(name = "application_type")
    @Enumerated(EnumType.ORDINAL)
    private ApplicationType applicationType = ApplicationType.WEB;

    @Column(name = "client_id", length = 100)
    private String clientId = IdUtil.fastSimpleUUID();

    @Column(name = "client_secret", length = 100)
    private String clientSecret = IdUtil.fastSimpleUUID();

    @Column(name = "client_secret_expires_at")
    private LocalDateTime clientSecretExpiresAt;

    @Column(name = "redirect_uris", length = 1000)
    private String redirectUris;

    @Column(name = "authorization_grant_types", length = 1000)
    private String authorizationGrantTypes;

    @Column(name = "client_authentication_methods", length = 1000)
    private String clientAuthenticationMethods;

    @Column(name = "require_proof_key")
    private Boolean requireProofKey = Boolean.FALSE;

    @Column(name = "require_authorization_consent")
    private Boolean requireAuthorizationConsent = Boolean.TRUE;

    @Column(name = "jwk_set_url", length = 1000)
    private String jwkSetUrl;

    @Column(name = "signing_algorithm")
    @Enumerated(EnumType.ORDINAL)
    private Signature authenticationSigningAlgorithm;

    @Column(name = "access_token_format")
    @Enumerated(EnumType.ORDINAL)
    private TokenFormat accessTokenFormat = TokenFormat.REFERENCE;

    @Column(name = "access_token_validity")
    private Duration accessTokenValidity = Duration.ofMinutes(5);

    @Column(name = "reuse_refresh_tokens")
    private Boolean reuseRefreshTokens = Boolean.TRUE;

    @Column(name = "refresh_token_validity")
    private Duration refreshTokenValidity = Duration.ofMinutes(60);

    @Column(name = "authorization_code_ttl")
    private Duration authorizationCodeTtl = Duration.ofMinutes(5);

    @Column(name = "signature_algorithm")
    @Enumerated(EnumType.ORDINAL)
    private Signature idTokenSignatureAlgorithm = Signature.RS256;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_APPLICATION_SCOPE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "oauth2_application_scope",
            joinColumns = {@JoinColumn(name = "application_id")},
            inverseJoinColumns = {@JoinColumn(name = "scope_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"application_id", "scope_id"})},
            indexes = {@Index(name = "oauth2_application_scope_aid_idx", columnList = "application_id"), @Index(name = "oauth2_application_scope_sid_idx", columnList = "scope_id")})
    private Set<OAuth2Scope> scopes = new HashSet<>();

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public String getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(String authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public String getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(String clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public Boolean getRequireProofKey() {
        return requireProofKey;
    }

    public void setRequireProofKey(Boolean requireProofKey) {
        this.requireProofKey = requireProofKey;
    }

    public Boolean getRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }

    public void setRequireAuthorizationConsent(Boolean requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
    }

    public String getJwkSetUrl() {
        return jwkSetUrl;
    }

    public void setJwkSetUrl(String jwkSetUrl) {
        this.jwkSetUrl = jwkSetUrl;
    }

    public Duration getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Duration accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Boolean getReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public void setReuseRefreshTokens(Boolean reuseRefreshTokens) {
        this.reuseRefreshTokens = reuseRefreshTokens;
    }

    public Duration getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Duration refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public Signature getIdTokenSignatureAlgorithm() {
        return idTokenSignatureAlgorithm;
    }

    public void setIdTokenSignatureAlgorithm(Signature signature) {
        this.idTokenSignatureAlgorithm = signature;
    }

    public Set<OAuth2Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Set<OAuth2Scope> scopes) {
        this.scopes = scopes;
    }

    public LocalDateTime getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(LocalDateTime clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
    }

    public Signature getAuthenticationSigningAlgorithm() {
        return authenticationSigningAlgorithm;
    }

    public void setAuthenticationSigningAlgorithm(Signature authenticationSigningAlgorithm) {
        this.authenticationSigningAlgorithm = authenticationSigningAlgorithm;
    }

    public TokenFormat getAccessTokenFormat() {
        return accessTokenFormat;
    }

    public void setAccessTokenFormat(TokenFormat accessTokenFormat) {
        this.accessTokenFormat = accessTokenFormat;
    }

    public Duration getAuthorizationCodeTtl() {
        return authorizationCodeTtl;
    }

    public void setAuthorizationCodeTtl(Duration authorizationCodeTtl) {
        this.authorizationCodeTtl = authorizationCodeTtl;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("applicationId", applicationId)
                .add("applicationName", applicationName)
                .add("abbreviation", abbreviation)
                .add("logo", logo)
                .add("homepage", homepage)
                .add("applicationType", applicationType)
                .add("clientId", clientId)
                .add("clientSecret", clientSecret)
                .add("clientSecretExpiresAt", clientSecretExpiresAt)
                .add("redirectUris", redirectUris)
                .add("authorizationGrantTypes", authorizationGrantTypes)
                .add("clientAuthenticationMethods", clientAuthenticationMethods)
                .add("requireProofKey", requireProofKey)
                .add("requireAuthorizationConsent", requireAuthorizationConsent)
                .add("jwkSetUrl", jwkSetUrl)
                .add("authenticationSigningAlgorithm", authenticationSigningAlgorithm)
                .add("accessTokenFormat", accessTokenFormat)
                .add("accessTokenValidity", accessTokenValidity)
                .add("reuseRefreshTokens", reuseRefreshTokens)
                .add("refreshTokenValidity", refreshTokenValidity)
                .add("idTokenSignatureAlgorithm", idTokenSignatureAlgorithm)
                .toString();
    }
}
