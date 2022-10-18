package com.kemido.pay.wechat.definition;

import com.kemido.pay.core.exception.PaymentProfileIdIncorrectException;
import com.kemido.pay.core.exception.PaymentProfileNotFoundException;
import com.kemido.pay.wechat.properties.WxpayProperties;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: 微信支付模版 </p>
 */
public class WxpayPaymentTemplate {

    private static final Logger log = LoggerFactory.getLogger(WxpayPaymentTemplate.class);

    private final WxpayProfileStorage wxpayProfileStorage;
    private final WxpayProperties wxpayProperties;

    public WxpayPaymentTemplate(WxpayProfileStorage wxpayProfileStorage, WxpayProperties wxpayProperties) {
        this.wxpayProfileStorage = wxpayProfileStorage;
        this.wxpayProperties = wxpayProperties;
    }

    private WxpayProfileStorage getWxpayProfileStorage() {
        return wxpayProfileStorage;
    }

    private WxpayProperties getWxpayProperties() {
        return wxpayProperties;
    }

    private WxpayProfile getProfile(String identity) {
        WxpayProfile wxpayProfile = getWxpayProfileStorage().getProfile(identity);
        if (ObjectUtils.isNotEmpty(wxpayProfile)) {
            return wxpayProfile;
        } else {
            throw new PaymentProfileNotFoundException("Payment profile for " + identity + " not found.");
        }
    }

    private WxpayPaymentExecuter getProcessor(Boolean sandbox, WxpayProfile wxpayProfile) {

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(wxpayProfile.getAppId());
        payConfig.setMchId(wxpayProfile.getMchId());
        payConfig.setMchKey(wxpayProfile.getMchKey());
        payConfig.setSubAppId(wxpayProfile.getSubAppId());
        payConfig.setSubMchId(wxpayProfile.getSubMchId());
        payConfig.setKeyPath(wxpayProfile.getKeyPath());

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(sandbox);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return new WxpayPaymentExecuter(wxPayService);
    }

    public WxpayPaymentExecuter getProcessor(String identity) {

        String id = StringUtils.isNotBlank(identity) ? identity : getWxpayProperties().getDefaultProfile();

        if (StringUtils.isBlank(id)) {
            throw new PaymentProfileIdIncorrectException("Payment profile incorrect, or try to set default profile id.");
        }

        WxpayProfile wxpayProfile = getProfile(identity);
        return getProcessor(getWxpayProperties().getSandbox(), wxpayProfile);
    }

}
