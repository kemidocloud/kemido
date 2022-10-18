package com.kemido.protect.web.tenant.interceptor;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.constants.HttpHeaders;
import com.kemido.assistant.core.thread.TenantContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 多租户拦截器 </p>
 */
public class MultiTenancyInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(MultiTenancyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURI();
        String tenantId = request.getHeader(HttpHeaders.X_HERODOTUS_TENANT_ID);
        if (StringUtils.isBlank(tenantId)) {
            tenantId = BaseConstants.DEFAULT_TENANT_ID;
        }

        log.debug("[Kemido] |- Tenant Interceptor got tenant is [{}] for request [{}].", tenantId, path);
        TenantContextHolder.setTenantId(tenantId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String path = request.getRequestURI();
        TenantContextHolder.clear();
        log.debug("[Kemido] |- Tenant Interceptor clear tenantId for request [{}].", path);
    }
}
