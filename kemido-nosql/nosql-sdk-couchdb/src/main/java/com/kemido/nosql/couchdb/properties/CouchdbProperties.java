package com.kemido.nosql.couchdb.properties;

import com.kemido.nosql.core.constants.NosqlConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: CouchDB 配置属性 </p>
 */
@ConfigurationProperties(prefix = NosqlConstants.PROPERTY_NOSQL_COUCHDB)
public class CouchdbProperties {

    /**
     * CouchDB API接口基础地址
     */
    private String endpoint;
    /**
     * CouchDB 管理员用户名
     */
    private String username;
    /**
     * CouchDB 管理员用户名
     */
    private String password;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
