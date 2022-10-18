package com.kemido.web.core.definition;

import com.kemido.web.core.context.ServiceContext;
import com.kemido.web.core.domain.RequestMapping;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: RequestMapping 扫描管理器 </p>
 */
public interface RequestMappingScanManager extends ApplicationStrategyEvent<List<RequestMapping>> {

    /**
     * 获取是否执行扫描的标记注解。
     *
     * @return 标记注解
     */
    Class<? extends Annotation> getScanAnnotationClass();

    /**
     * Request Mapping 收集汇总的服务名称
     *
     * @return 服务名称
     */
    String getDestinationServiceName();

    /**
     * 执行本地数据存储
     *
     * @param requestMappings 扫描到的RequestMapping
     */
    void postLocalStorage(List<RequestMapping> requestMappings);

    /**
     * 发布远程事件，传送RequestMapping
     *
     * @param requestMappings 扫描到的RequestMapping
     */
    default void process(List<RequestMapping> requestMappings) {
        postLocalStorage(requestMappings);
        postProcess(getDestinationServiceName(), requestMappings);
    }

    /**
     * 是否满足执行扫描的条件。
     * 根据扫描标记注解 {@link #getScanAnnotationClass()} 以及 是否是分布式架构 决定是否执行接口的扫描。
     * <p>
     * 分布式架构根据注解判断是否扫描，单体架构直接扫描即可无须判断
     *
     * @return true 执行， false 不执行
     */
    default boolean isPerformScan() {
        if (ServiceContext.getInstance().isDistributedArchitecture()) {
            if (ObjectUtils.isEmpty(getScanAnnotationClass())) {
                return false;
            }

            Map<String, Object> content = ServiceContext.getInstance().getApplicationContext().getBeansWithAnnotation(getScanAnnotationClass());
            return !MapUtils.isEmpty(content);
        }

        return true;
    }
}
