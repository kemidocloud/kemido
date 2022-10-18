package com.kemido.oauth2.core.definition.strategy;

import com.kemido.assistant.core.domain.AccessPrincipal;
import com.kemido.oauth2.core.definition.domain.KemidoUser;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 系统用户服务策略定义 </p>
 */
public interface StrategyUserDetailsService {

    KemidoUser findUserDetailsByUsername(String userName) throws AuthenticationException;

    KemidoUser findUserDetailsBySocial(String source, AccessPrincipal accessPrincipal) throws AuthenticationException;
}
