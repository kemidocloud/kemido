package com.kemido.nosql.influxdb.configuration;

import com.kemido.nosql.influxdb.support.InfluxdbTemplate;
import com.kemido.nosql.influxdb.annotation.ConditionalOnInfluxdbEnabled;
import com.kemido.nosql.influxdb.properties.InfluxdbProperties;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: Influxdb 配置 </p>
 */
@Configuration
@ConditionalOnInfluxdbEnabled
@EnableConfigurationProperties({InfluxdbProperties.class})
public class InfluxdbConfiguration {

    private static final Logger log = LoggerFactory.getLogger(InfluxdbConfiguration.class);

    @PostConstruct
    public void init() {
        log.info("[Kemido] |- Plugin [Kemido Influxdb] Auto Configure.");
    }

    @Bean
    public InfluxDB influxdb(InfluxdbProperties influxdbProperties) {

        InfluxDB influxdb;
        if (StringUtils.isNotBlank(influxdbProperties.getUsername()) && StringUtils.isNotBlank(influxdbProperties.getPassword())) {
            influxdb = InfluxDBFactory.connect(influxdbProperties.getEndpoint(), influxdbProperties.getUsername(), influxdbProperties.getPassword());
        } else {
            influxdb = InfluxDBFactory.connect(influxdbProperties.getEndpoint());
        }

        try {
            /**
             * 异步插入：
             * enableBatch这里第一个是point的个数，第二个是时间，单位毫秒
             * point的个数和时间是联合使用的，如果满100条或者2000毫秒
             * 满足任何一个条件就会发送一次写的请求。
             */
            influxdb.setDatabase(influxdbProperties.getDatabase()).enableBatch(100, 2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("[Kemido] |- Influxdb set database catch error.", e);
        } finally {
            influxdb.setRetentionPolicy("autogen");
        }
        influxdb.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxdb;
    }

    @Bean
    @ConditionalOnBean(InfluxDB.class)
    public InfluxdbTemplate influxdbTemplate(InfluxDB influxdb) {
        InfluxdbTemplate influxdbTemplate = new InfluxdbTemplate(influxdb);
        log.trace("[Kemido] |- Bean [Influxdb Template Auto Configure.");
        return influxdbTemplate;
    }
}
