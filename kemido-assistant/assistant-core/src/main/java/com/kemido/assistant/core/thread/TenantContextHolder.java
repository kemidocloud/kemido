package com.kemido.assistant.core.thread;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * <p>Description: 存储/获取当前线程的租户信息 </p>
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CURRENT_CONTEXT = new TransmittableThreadLocal<>();

    public static String getTenantId() {
        return CURRENT_CONTEXT.get();
    }

    public static void setTenantId(final String tenantId) {
        CURRENT_CONTEXT.set(tenantId);
    }

    public static void clear() {
        CURRENT_CONTEXT.remove();
    }
}
