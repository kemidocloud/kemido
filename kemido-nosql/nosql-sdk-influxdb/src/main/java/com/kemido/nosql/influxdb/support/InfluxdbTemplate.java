package com.kemido.nosql.influxdb.support;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Influxdb 操作模版 </p>
 */
@Component
public class InfluxdbTemplate {

    private static final Logger log = LoggerFactory.getLogger(InfluxdbTemplate.class);

    private final InfluxDB influxdb;

    public InfluxdbTemplate(InfluxDB influxdb) {
        this.influxdb = influxdb;
    }

    /**
     * 测试连接是否正常
     *
     * @return true 正常
     */
    public boolean ping() {
        boolean isConnected = false;
        Pong pong;
        try {
            pong = influxdb.ping();
            if (pong != null) {
                isConnected = true;
            }
        } catch (Exception e) {
            log.error("[Kemido] |- Influxdb ping the connection error.", e);
        }
        return isConnected;
    }
}
