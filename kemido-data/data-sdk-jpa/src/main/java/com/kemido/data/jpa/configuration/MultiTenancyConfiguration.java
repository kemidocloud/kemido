package com.kemido.data.jpa.configuration;

import com.kemido.data.jpa.annotation.ConditionalOnMultiTenancyEnabled;
import com.kemido.data.jpa.properties.MultiTenancyProperties;
import com.kemido.data.jpa.tenancy.MultiTenancyConnectionProvider;
import com.kemido.data.jpa.tenancy.MultiTenancyDataSourceLookup;
import com.kemido.data.jpa.tenancy.MultiTenancyIdentifierResolver;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>Description: Data JPA 模块 多租户配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMultiTenancyEnabled
@EnableConfigurationProperties(MultiTenancyProperties.class)
public class MultiTenancyConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DataJpaConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Kemido] |- SDK [Engine Data Multi Tenancy] Auto Configure.");
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public DataSourceLookup dataSourceLookup(DataSource dataSource, MultiTenancyProperties multiTenancyProperties) {
        MultiTenancyDataSourceLookup multiTenancyDataSourceLookup = new MultiTenancyDataSourceLookup(dataSource, multiTenancyProperties);
        log.debug("[Kemido] |- Bean [Multi Tenancy DataSource Lookup] Auto Configure.");
        return multiTenancyDataSourceLookup;
    }

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource dataSource, DataSourceLookup dataSourceLookup) {
        MultiTenancyConnectionProvider multiTenancyConnectionProvider = new MultiTenancyConnectionProvider(dataSource, dataSourceLookup);
        log.debug("[Kemido] |- Bean [Multi Tenancy Connection Provider] Auto Configure.");
        return multiTenancyConnectionProvider;
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        MultiTenancyIdentifierResolver multiTenancyIdentifierResolver = new MultiTenancyIdentifierResolver();
        log.debug("[Kemido] |- Bean [Multi Tenancy Connection Provider] Auto Configure.");
        return multiTenancyIdentifierResolver;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, HibernateProperties hibernateProperties, JpaVendorAdapter jpaVendorAdapter, JpaProperties jpaProperties, MultiTenancyProperties multiTenancyProperties, MultiTenantConnectionProvider multiTenantConnectionProvider, CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

        Supplier<String> defaultDdlMode = hibernateProperties::getDdlAuto;
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings().ddlAuto(defaultDdlMode));

        properties.put(Environment.MULTI_TENANT, multiTenancyProperties.getTenancyStrategy());
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        //此处不能省略，哪怕你使用了 @EntityScan，实际上 @EntityScan 会失效
        emf.setPackagesToScan(multiTenancyProperties.getPackageToScan());
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setJpaPropertyMap(properties);
        return emf;
    }
}
