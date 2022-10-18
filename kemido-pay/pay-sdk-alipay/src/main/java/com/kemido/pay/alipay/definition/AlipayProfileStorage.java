package com.kemido.pay.alipay.definition;

/**
 * <p>Description: 配置信息存储定义 </p>
 */
public abstract class AlipayProfileStorage {

    /**
     * 获取支付配置信息
     *
     * @param identity 配置信息唯一标识
     * @return 唯一标识对应的配置信息 {@link AlipayProfile}
     */
    public abstract AlipayProfile getProfile(String identity);
}
