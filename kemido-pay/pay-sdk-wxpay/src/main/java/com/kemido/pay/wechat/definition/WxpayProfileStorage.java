package com.kemido.pay.wechat.definition;

/**
 * <p>Description: 微信支付配置信息存储定义 </p>
 */
public abstract class WxpayProfileStorage {

    /**
     * 获取支付配置信息
     *
     * @param identity 自定义的支付配置信息识别代码
     * @return 微信支付配置信息
     */
    public abstract WxpayProfile getProfile(String identity);
}
