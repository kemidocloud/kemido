package com.kemido.data.autoconfigure;

import com.kemido.data.mybatis.plus.configuration.MybatisPlusConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * <p>Description: Data组件自动注入 </p>
 */
@Configuration(proxyBeanMethods = false)
@Import({
        MybatisPlusConfiguration.class
})
public class AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[Kemido] |- Starter [Engine Data Starter] Auto Configure.");
    }
}
