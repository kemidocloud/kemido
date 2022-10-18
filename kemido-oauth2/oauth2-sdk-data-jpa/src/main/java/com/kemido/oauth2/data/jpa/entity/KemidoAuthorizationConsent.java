package com.kemido.oauth2.data.jpa.entity;

import com.kemido.assistant.core.definition.domain.AbstractEntity;
import com.kemido.oauth2.core.constants.OAuth2Constants;
import com.kemido.oauth2.data.jpa.generator.KemidoAuthorizationConsentId;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * <p>Description: OAuth2 认证确认信息实体 </p>
 */
@Entity
@Table(name = "oauth2_authorization_consent", indexes = {
        @Index(name = "oauth2_authorization_consent_rcid_idx", columnList = "registered_client_id"),
        @Index(name = "oauth2_authorization_consent_pn_idx", columnList = "principal_name")})
@IdClass(KemidoAuthorizationConsentId.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_AUTHORIZATION_CONSENT)
public class KemidoAuthorizationConsent extends AbstractEntity {

    @Id
    @Column(name = "registered_client_id", nullable = false, length = 100)
    private String registeredClientId;

    @Id
    @Column(name = "principal_name", nullable = false, length = 200)
    private String principalName;

    @Column(name = "authorities", nullable = false, length = 1000)
    private String authorities;

    public String getRegisteredClientId() {
        return registeredClientId;
    }

    public void setRegisteredClientId(String registeredClientId) {
        this.registeredClientId = registeredClientId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KemidoAuthorizationConsent that = (KemidoAuthorizationConsent) o;
        return Objects.equal(registeredClientId, that.registeredClientId) && Objects.equal(principalName, that.principalName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(registeredClientId, principalName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("registeredClientId", registeredClientId)
                .add("principalName", principalName)
                .add("authorities", authorities)
                .toString();
    }
}
