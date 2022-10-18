package com.kemido.pay.wechat.definition;

import com.github.binarywang.wxpay.service.WxPayService;

/**
 * <p>Description: 微信支付执行器 </p>
 */
public class WxpayPaymentExecuter {

    private WxPayService wxPayService;

    public WxpayPaymentExecuter(WxPayService wxPayService) {
        this.wxPayService = wxPayService;
    }

    private WxPayService getWxPayService() {
        return wxPayService;
    }
}
