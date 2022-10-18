package com.kemido.nosql.influxdb.properties;

import com.kemido.nosql.core.constants.NosqlConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Influxdb 配置参数 </p>
 */
@ConfigurationProperties(prefix = NosqlConstants.PROPERTY_NOSQL_INFLUXDB)
public class InfluxdbProperties {

    /**
     * Influxdb 1.X 用户名
     */
    private String username;
    /**
     * Influxdb 1.X 密码
     */
    private String password;
    /**
     * Influxdb 1.X 连接url
     */
    private String endpoint;
    /**
     * Influxdb 1.X database
     */
    private String database;

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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("password", password)
                .add("endpoint", endpoint)
                .add("database", database)
                .toString();
    }
}
