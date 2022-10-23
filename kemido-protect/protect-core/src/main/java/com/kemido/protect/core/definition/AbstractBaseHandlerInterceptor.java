package com.kemido.protect.core.definition;

import com.kemido.assistant.core.constants.HttpHeaders;
import com.kemido.assistant.core.constants.SymbolConstants;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Description: 基础拦截器 </p>
 *
 * 定义拦截器通用方法
 */
public abstract class AbstractBaseHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AbstractBaseHandlerInterceptor.class);

    protected String generateRequestKey(HttpServletRequest request) {

        String sessionId = request.getHeader(HttpHeaders.X_HERODOTUS_SESSION);
        if (StringUtils.isBlank(sessionId)) {
            HttpSession session = request.getSession();
            sessionId = session.getId();
        }

        String url = request.getRequestURI();
        String method = request.getMethod();

        if (StringUtils.isNotBlank(sessionId)) {
            String key = SecureUtil.md5(sessionId + SymbolConstants.COLON + url + SymbolConstants.COLON + method);
            log.debug("[Kemido] |- IdempotentInterceptor key is [{}].", key);
            return key;
        } else {
            log.warn("[Kemido] |- IdempotentInterceptor cannot create key, because sessionId is null.");
            return null;
        }
    }
}