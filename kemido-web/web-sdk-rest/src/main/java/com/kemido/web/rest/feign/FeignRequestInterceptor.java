package com.kemido.web.rest.feign;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.assistant.core.thread.TenantContextHolder;
import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>Description: 自定义FeignRequestInterceptor </p>
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();

        if (httpServletRequest != null) {
            Map<String, String> headers = ServletUtil.getHeaderMap(httpServletRequest);
            // 传递所有请求头,防止部分丢失
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                // 跳过content-length值的复制。因为服务之间调用需要携带一些用户信息之类的 所以实现了Feign的RequestInterceptor拦截器复制请求头，复制的时候是所有头都复制的,可能导致Content-length长度跟body不一致
                // @see https://blog.csdn.net/qq_39986681/article/details/107138740
                if (StringUtils.equalsIgnoreCase(key, HttpHeaders.CONTENT_LENGTH)) {
                    continue;
                }

                // 解决 UserAgent 信息被修改后，AppleWebKit/537.36 (KHTML,like Gecko)部分存在非法字符的问题
                if (StringUtils.equalsIgnoreCase(key, HttpHeaders.USER_AGENT)) {
                    value = StringUtils.replace(value, SymbolConstants.NEW_LINE, SymbolConstants.BLANK);
                    entry.setValue(value);
                }

                requestTemplate.header(key, value);
            }

            log.debug("[Kemido] |- Feign Request Interceptor copy all need transfer header!");

            // 检查 Tenant Id 的可用性。
            String tenantIdHeader = com.kemido.assistant.core.constants.HttpHeaders.X_HERODOTUS_TENANT_ID;
            if (!headers.containsKey(tenantIdHeader)) {
                String tenantId = TenantContextHolder.getTenantId();
                if (StringUtils.isNotBlank(tenantId)) {
                    log.info("[Kemido] |- Feign Request Interceptor Tenant is : {}", tenantId);
                    requestTemplate.header(tenantIdHeader, tenantId);
                }
            }

            // 微服务之间传递的唯一标识,区分大小写所以通过httpServletRequest查询
            if (headers.containsKey(HttpHeaders.X_REQUEST_ID)) {
                String traceId = headers.get(HttpHeaders.X_REQUEST_ID);
                MDC.put("traceId", traceId);
                log.info("[Kemido] |- Feign Request Interceptor Trace: {}", traceId);
            }
        }

        log.trace("[Kemido] |- Feign Request Interceptor [{}]", requestTemplate.toString());
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            log.error("[Kemido] |- Feign Request Interceptor can not get Request.");
            return null;
        }
    }
}
