package com.kemido.cache.redisson.properties;

import com.kemido.cache.core.constants.CacheConstants;
import com.kemido.assistant.core.constants.SymbolConstants;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Redisson 配置 </p>
 */
@ConfigurationProperties(prefix = CacheConstants.PROPERTY_REDIS_REDISSON)
public class RedissonProperties {

    /**
     * Redisson 使用模式
     */
    public enum Mode {
        /**
         * 单机
         */
        SINGLE,
        /**
         * 哨兵
         */
        SENTINEL,
        /**
         * 集群
         */
        CLUSTER
    }

    /**
     * 是否开启 Redisson
     */
    private Boolean enabled = false;

    /**
     * Redis 模式
     */
    private Mode mode = Mode.SINGLE;

    /**
     * 配置文件路径
     */
    private String config;

    /**
     * 单体配置
     */
    private SingleServerConfig singleServerConfig;
    /**
     * 集群配置
     */
    private ClusterServersConfig clusterServersConfig;

    /**
     * 哨兵配置
     */
    private SentinelServersConfig sentinelServersConfig;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public SingleServerConfig getSingleServerConfig() {
        return singleServerConfig;
    }

    public void setSingleServerConfig(SingleServerConfig singleServerConfig) {
        this.singleServerConfig = singleServerConfig;
    }

    public ClusterServersConfig getClusterServersConfig() {
        return clusterServersConfig;
    }

    public void setClusterServersConfig(ClusterServersConfig clusterServersConfig) {
        this.clusterServersConfig = clusterServersConfig;
    }

    public SentinelServersConfig getSentinelServersConfig() {
        return sentinelServersConfig;
    }

    public void setSentinelServersConfig(SentinelServersConfig sentinelServersConfig) {
        this.sentinelServersConfig = sentinelServersConfig;
    }

    public boolean isExternalConfig() {
        return StringUtils.isNotBlank(this.getConfig());
    }

    public boolean isYamlConfig() {
        if (this.isExternalConfig()) {
            return StringUtils.endsWithIgnoreCase(this.getConfig(), SymbolConstants.SUFFIX_YAML);
        } else {
            return false;
        }
    }

    public boolean isJsonConfig() {
        if (this.isExternalConfig()) {
            return StringUtils.endsWithIgnoreCase(this.getConfig(), SymbolConstants.SUFFIX_JSON);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("enabled", enabled)
                .add("mode", mode)
                .add("config", config)
                .toString();
    }
}
