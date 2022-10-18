package com.kemido.facility.log.properties;

import ch.qos.logback.core.util.Duration;
import com.kemido.facility.core.constants.FacilityConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 日志中心配置 </p>
 */
@ConfigurationProperties(prefix = FacilityConstants.PROPERTY_PREFIX_LOG_CENTER)
public class LogProperties {

    /**
     * 日志中心的logstash地址。
     */
    private String serverAddr = "127.0.0.1:5044";
    /**
     * 日志级别，默认为INFO
     */
    private LogLevel logLevel = LogLevel.INFO;
    /**
     * 保持活动持续时间，默认5分钟，单位：分钟
     */
    private Duration keepAliveDuration = Duration.buildByMinutes(5);

    /**
     * 尝试连接到目标间隔时间，默认30秒， 单位：秒
     */
    private Duration reconnectionDelay = Duration.buildBySeconds(30);
    /**
     * 日志写入超时时间，默认1分钟，单位：分钟
     */
    private Duration writeTimeout = Duration.buildByMinutes(1);
    /**
     * 日志级别配置
     */
    private Map<String, LogLevel> loggers = new HashMap<>();

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public Map<String, LogLevel> getLoggers() {
        return loggers;
    }

    public void setLoggers(Map<String, LogLevel> loggers) {
        this.loggers = loggers;
    }

    public Duration getKeepAliveDuration() {
        return keepAliveDuration;
    }

    public void setKeepAliveDuration(Duration keepAliveDuration) {
        this.keepAliveDuration = keepAliveDuration;
    }

    public Duration getReconnectionDelay() {
        return reconnectionDelay;
    }

    public void setReconnectionDelay(Duration reconnectionDelay) {
        this.reconnectionDelay = reconnectionDelay;
    }

    public Duration getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Duration writeTimeout) {
        this.writeTimeout = writeTimeout;
    }
}
