package com.kemido.data.jpa.hibernate.cache.spi;

import cn.hutool.extra.spring.SpringUtil;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.cfg.spi.DomainDataRegionBuildingContext;
import org.hibernate.cache.cfg.spi.DomainDataRegionConfig;
import org.hibernate.cache.spi.support.DomainDataStorageAccess;
import org.hibernate.cache.spi.support.RegionFactoryTemplate;
import org.hibernate.cache.spi.support.StorageAccess;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.cache.CacheManager;

import java.util.Map;

/**
 * <p>Description: 自定义Hibernate二级缓存RegionFactory </p>
 */
public class KemidoRegionFactory extends RegionFactoryTemplate {

    private CacheManager cacheManager;

    @Override
    protected StorageAccess createQueryResultsRegionStorageAccess(String regionName, SessionFactoryImplementor sessionFactory) {
        return new KemidoDomainDataStorageAccess(cacheManager.getCache(regionName));
    }

    @Override
    protected StorageAccess createTimestampsRegionStorageAccess(String regionName, SessionFactoryImplementor sessionFactory) {
        return new KemidoDomainDataStorageAccess(cacheManager.getCache(regionName));
    }

    @Override
    protected DomainDataStorageAccess createDomainDataStorageAccess(DomainDataRegionConfig regionConfig, DomainDataRegionBuildingContext buildingContext) {
        return new KemidoDomainDataStorageAccess(cacheManager.getCache(regionConfig.getRegionName()));
    }

    @Override
    protected void prepareForUse(SessionFactoryOptions settings, Map configValues) {
        this.cacheManager = SpringUtil.getBean("kemidoCacheManager");
    }

    @Override
    protected void releaseFromUse() {
        cacheManager = null;
    }
}
