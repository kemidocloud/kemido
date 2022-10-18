package com.kemido.web.core.definition;

import com.kemido.assistant.core.definition.event.StrategyEvent;
import com.kemido.web.core.context.ServiceContext;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: 应用策略事件 </p>
 */
public interface ApplicationStrategyEvent<T> extends StrategyEvent<T> {

    @Override
    default boolean isLocal(String destinationService) {
        return !ServiceContext.getInstance().isDistributedArchitecture() || StringUtils.equals(ServiceContext.getInstance().getApplicationName(), destinationService);
    }

    /**
     * 发送事件
     *
     * @param data               事件携带数据
     * @param destinationService 接收远程事件目的地
     */
    default void postProcess(String destinationService, T data ) {
        postProcess(ServiceContext.getInstance().getOriginService(), destinationService, data);
    }
}
