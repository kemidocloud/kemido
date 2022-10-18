package com.kemido.protect.web.tenant.interceptor;

import com.kemido.assistant.core.constants.BaseConstants;
import com.kemido.assistant.core.constants.HttpHeaders;
import com.kemido.assistant.core.thread.TenantContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>Description: 多租户过滤器 </p>
 */
public class MultiTenancyFilter extends GenericFilterBean {

    private static final Logger log = LoggerFactory.getLogger(MultiTenancyFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String tenantId = request.getHeader(HttpHeaders.X_HERODOTUS_TENANT_ID);
        String path = request.getRequestURI();
        if (StringUtils.isBlank(tenantId)) {
            TenantContextHolder.setTenantId(BaseConstants.DEFAULT_TENANT_ID);
        } else {
            log.debug("[Kemido] |- Fetch the tenant id [{}] from request [{}].", tenantId, path);
            TenantContextHolder.setTenantId(tenantId);
        }

        filterChain.doFilter(servletRequest, servletResponse);
        TenantContextHolder.clear();
    }

    @Override
    public void destroy() {
        TenantContextHolder.clear();
    }
}
