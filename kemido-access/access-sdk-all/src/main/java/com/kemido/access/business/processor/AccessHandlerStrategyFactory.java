package com.kemido.access.business.processor;

import com.kemido.access.core.definition.AccessHandler;
import com.kemido.access.core.definition.AccessResponse;
import com.kemido.access.core.definition.AccessUserDetails;
import com.kemido.access.core.exception.AccessHandlerNotFoundException;
import com.kemido.access.core.exception.IllegalAccessArgumentException;
import com.kemido.assistant.core.domain.AccessPrincipal;
import com.kemido.assistant.core.enums.AccountType;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: Access Handler 工厂 </p>
 * <p>
 * 通过该工厂模式，对接入的常规操作进行封装。避免导入引用各个组件，导致耦合性增大
 * <p>
 * 本处使用基于Spring Boot 的工厂模式
 * {@see :https://www.pianshen.com/article/466978086/}
 */
public class AccessHandlerStrategyFactory {

    private static final Logger log = LoggerFactory.getLogger(AccessHandlerStrategyFactory.class);

    @Autowired
    private final Map<String, AccessHandler> handlers = new ConcurrentHashMap<>();

    public AccessResponse preProcess(String source, String core, String... params) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(source);
        return socialAuthenticationHandler.preProcess(core, params);
    }

    public AccessResponse preProcess(AccountType accountType, String core, String... params) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(accountType);
        return socialAuthenticationHandler.preProcess(core, params);
    }

    public AccessUserDetails findAccessUserDetails(String source, AccessPrincipal accessPrincipal) {
        AccessHandler socialAuthenticationHandler = this.getAccessHandler(source);
        AccessUserDetails accessUserDetails = socialAuthenticationHandler.loadUserDetails(source, accessPrincipal);

        log.debug("[Kemido] |- AccessHandlerFactory findAccessUserDetails.");
        return accessUserDetails;
    }

    public AccessHandler getAccessHandler(String source) {
        if (ObjectUtils.isEmpty(source)) {
            throw new IllegalAccessArgumentException("Cannot found SocialProvider");
        }

        AccountType accountType = AccountType.getAccountType(source);
        if (ObjectUtils.isEmpty(accountType)) {
            throw new IllegalAccessArgumentException("Cannot parse the source parameter.");
        }

        return getAccessHandler(accountType);
    }

    public AccessHandler getAccessHandler(AccountType accountType) {
        String handlerName = accountType.getHandler();
        AccessHandler socialAuthenticationHandler = handlers.get(handlerName);
        if (ObjectUtils.isNotEmpty(socialAuthenticationHandler)) {
            return socialAuthenticationHandler;
        } else {
            throw new AccessHandlerNotFoundException("Can not found Social Handler for " + handlerName);
        }
    }
}
