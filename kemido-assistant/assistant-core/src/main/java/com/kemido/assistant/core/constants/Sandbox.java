package com.kemido.assistant.core.constants;

/**
 * <p>Description: 统一的 Sandbox 管理 </p>
 */
public class Sandbox {

    /**
     * 支付宝网关地址
     */
    private static final String ALIPAY_PRODUCTION_SERVER_URL = "https://openapi.alipay.com/gateway.do";
    private static final String ALIPAY_SANDBOX_SERVER_URL = "https://openapi.alipaydev.com/gateway.do";

    public static String getAliPayServerUrl(boolean sandbox) {
        return sandbox ? ALIPAY_SANDBOX_SERVER_URL : ALIPAY_PRODUCTION_SERVER_URL;
    }
}
