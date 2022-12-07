package com.kemido.web.scan.configuration;

import com.kemido.web.core.definition.RequestMappingScanManager;
import com.kemido.web.scan.annotation.ConditionalOnScanEnabled;
import com.kemido.web.scan.processor.RequestMappingScanner;
import com.kemido.web.scan.properties.ScanProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Description: 接口扫描配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnScanEnabled
@EnableConfigurationProperties(ScanProperties.class)
@AutoConfigureAfter(RequestMappingScanManager.class)
public class ScanConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ScanConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Web Scan] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestMappingScanner requestMappingScanner(ScanProperties scanProperties, RequestMappingScanManager requestMappingScanManager) {
        RequestMappingScanner requestMappingScanner = new RequestMappingScanner(scanProperties, requestMappingScanManager);
        log.trace("[Kemido] |- Bean [Request Mapping Scanner] Auto Configure.");
        return requestMappingScanner;
    }
}
