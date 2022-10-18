package com.kemido.oauth2.compliance.definition;

import com.kemido.web.core.definition.ApplicationStrategyEvent;
import com.kemido.web.core.domain.UserStatus;

/**
 * <p>Description: 用户状态变更服务 </p>
 */
public interface AccountStatusChangeService extends ApplicationStrategyEvent<UserStatus> {

    /**
     * Request Mapping 收集汇总的服务名称
     *
     * @return 服务名称
     */
    String getDestinationServiceName();

    default void process(UserStatus status) {
        postProcess(getDestinationServiceName(), status);
    }

}
