package com.kemido.nosql.couchdb.configuration;

import com.kemido.nosql.couchdb.properties.CouchdbProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: CouchDB 配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CouchdbProperties.class})
@ComponentScan(basePackages = {
        "com.kemido.nosql.couchdb.service",
        "com.kemido.nosql.couchdb.controller",
})
public class CouchdbConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CouchdbConfiguration.class);

    @PostConstruct
    public void init() {
        log.info("[Kemido] |- SDK [Engine Nosql CouchDB] Auto Configure.");
    }
}
