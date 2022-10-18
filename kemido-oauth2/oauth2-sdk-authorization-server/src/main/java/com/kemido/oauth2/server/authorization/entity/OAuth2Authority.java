package com.kemido.oauth2.server.authorization.entity;

import com.kemido.data.core.entity.BaseSysEntity;
import com.kemido.oauth2.core.constants.OAuth2Constants;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <p>Description: 客户端权限 </p>
 */
@Entity
@Table(name = "oauth2_authority", indexes = {@Index(name = "oauth2_authority_id_idx", columnList = "authority_id")})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = OAuth2Constants.REGION_OAUTH2_AUTHORITY)
public class OAuth2Authority extends BaseSysEntity {

    @Id
    @GeneratedValue(generator = "oauth2-authority-uuid")
    @GenericGenerator(name = "oauth2-authority-uuid", strategy = "com.kemido.oauth2.server.authorization.generator.OAuth2AuthorityUUIDGenerator")
    @Column(name = "authority_id", length = 64)
    private String authorityId;

    @Column(name = "authority_code", length = 128)
    private String authorityCode;

    @Column(name = "service_id", length = 128)
    private String serviceId;

    @Column(name = "request_method", length = 20)
    private String requestMethod;

    @Column(name = "url", length = 2048)
    private String url;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("authorityId", authorityId)
                .add("authorityCode", authorityCode)
                .add("serviceId", serviceId)
                .add("requestMethod", requestMethod)
                .add("url", url)
                .toString();
    }
}
