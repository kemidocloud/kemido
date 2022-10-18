package com.kemido.assistant.core.constants;

/**
 * <p>Description: 自定义请求头 </p>
 */
public interface HttpHeaders {

    String UNKNOWN = "unknown";
    String PROXY_CLIENT_IP = "Proxy-Client-IP";
    String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    String X_REAL_IP = "X-Real-IP";
    String X_HERODOTUS_SESSION = "X-Kemido-Session";
    String X_HERODOTUS_FROM_IN = "X-Kemido-From-In";
    String X_HERODOTUS_TENANT_ID = "X-Kemido-Tenant-Id";
}
