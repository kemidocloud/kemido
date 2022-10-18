package com.kemido.cache.core.properties;

import com.kemido.cache.core.constants.CacheConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 缓存配置属性 </p>
 */
@ConfigurationProperties(prefix = CacheConstants.PROPERTY_PREFIX_CACHE)
public class CacheProperties extends Expire {

    /**
     * 是否允许存储空值
     */
    private Boolean allowNullValues = true;

    /**
     * 缓存名称转换分割符。默认值，"-"
     * <p>
     * 默认缓存名称采用 Redis Key 格式（使用 ":" 分割），使用 ":" 分割的字符串作为Map的Key，":"会丢失。
     * 指定一个分隔符，用于 ":" 分割符的转换
     */
    private String separator = "-";

    /**
     * 针对不同实体单独设置的过期时间，如果不设置，则统一使用默认时间。
     */
    private Map<String, Expire> expires = new HashMap<>();

    public Boolean getAllowNullValues() {
        return allowNullValues;
    }

    public void setAllowNullValues(Boolean allowNullValues) {
        this.allowNullValues = allowNullValues;
    }

    public Map<String, Expire> getExpires() {
        return expires;
    }

    public void setExpires(Map<String, Expire> expires) {
        this.expires = expires;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("allowNullValues", allowNullValues)
                .add("separator", separator)
                .toString();
    }
}
