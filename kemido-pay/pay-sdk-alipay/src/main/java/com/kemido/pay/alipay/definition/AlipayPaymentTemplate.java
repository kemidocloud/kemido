package com.kemido.pay.alipay.definition;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;
import com.kemido.event.core.local.LocalPaymentNotifyEvent;
import com.kemido.event.core.local.LocalPaymentReturnEvent;
import com.kemido.event.pay.remote.RemotePaymentNotifyEvent;
import com.kemido.event.pay.remote.RemotePaymentReturnEvent;
import com.kemido.pay.alipay.properties.AlipayProperties;
import com.kemido.pay.core.constants.PayConstants;
import com.kemido.pay.core.exception.PaymentProfileIdIncorrectException;
import com.kemido.pay.core.exception.PaymentProfileNotFoundException;
import com.kemido.pay.core.exception.PaymentSignatureCheckErrorException;
import com.kemido.web.core.context.ServiceContext;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>Description: 支付宝支付基础类 </p>
 */
public class AlipayPaymentTemplate {

    private static final Logger log = LoggerFactory.getLogger(AlipayPaymentTemplate.class);

    @CreateCache(name = PayConstants.CACHE_NAME_TOKEN_PAY, cacheType = CacheType.BOTH)
    private Cache<String, String> cache;

    private final AlipayProfileStorage alipayProfileStorage;
    private final AlipayProperties alipayProperties;

    private ApplicationContext applicationContext;
    private boolean monocoque;
    private String serviceId;

    public enum Feedback {
        /**
         * 支付宝反馈类型
         */
        PAYMENT_RETURN("return:"),
        PAYMENT_NOTIFY("notify:");

        private final String prefix;

        Feedback(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public AlipayPaymentTemplate(AlipayProfileStorage alipayProfileStorage, AlipayProperties alipayProperties) {
        this.alipayProfileStorage = alipayProfileStorage;
        this.alipayProperties = alipayProperties;
        this.serviceId = ServiceContext.getInstance().getApplicationName();
        this.monocoque = ServiceContext.getInstance().isDistributedArchitecture();
        this.applicationContext = ServiceContext.getInstance().getApplicationContext();
    }

    private AlipayProfileStorage getAlipayProfileStorage() {
        return alipayProfileStorage;
    }

    private AlipayProperties getAlipayProperties() {
        return alipayProperties;
    }

    private AlipayProfile getProfile(String identity) {
        AlipayProfile aliPayProfile = getAlipayProfileStorage().getProfile(identity);
        if (ObjectUtils.isNotEmpty(aliPayProfile)) {
            return aliPayProfile;
        } else {
            throw new PaymentProfileNotFoundException("Payment profile for " + identity + " not found.");
        }
    }

    private AlipayPaymentExecuter getProcessor(Boolean sandbox, Boolean certMode, AlipayProfile aliPayProfile) {

        AlipayClient alipayClient = AlipayClientBuilder.mode(sandbox, certMode)
                .setAppId(aliPayProfile.getAppId())
                .setAppPrivateKey(aliPayProfile.getAppPrivateKey())
                .setCharset(StandardCharsets.UTF_8.toString())
                .setAlipayPublicKey(aliPayProfile.getAlipayPublicKey())
                .setSignType(aliPayProfile.getSignType())
                .setAppCertPath(aliPayProfile.getAppCertPath())
                .setAlipayCertPath(aliPayProfile.getAlipayCertPath())
                .setAlipayRootCertPath(aliPayProfile.getAlipayRootCertPath())
                .build();

        return AlipayPaymentExecuter.create(alipayClient, certMode);
    }

    private Cache<String, String> getCache() {
        return cache;
    }

    private String getArea(String key, Feedback feedback) {
        return feedback.getPrefix() + key;
    }

    private void addCache(String key, String value, Feedback feedback) {
        String area = getArea(key, feedback);
        getCache().put(area, value);
    }

    private void deleteCache(String key, Feedback feedback) {
        String area = getArea(key, feedback);
        getCache().remove(area);
    }

    private String getCache(String key, Feedback feedback) {
        String area = getArea(key, feedback);
        return getCache().get(area);
    }

    public AlipayPaymentExecuter getProcessor(String tradeNo, String identity) {

        String id = StringUtils.isNotBlank(identity) ? identity : getAlipayProperties().getDefaultProfile();

        if (StringUtils.isBlank(id)) {
            throw new PaymentProfileIdIncorrectException("Payment profile incorrect, or try to set default profile id.");
        }

        AlipayProfile aliPayProfile = getProfile(identity);
        addCache(tradeNo, identity, Feedback.PAYMENT_NOTIFY);
        addCache(tradeNo, identity, Feedback.PAYMENT_RETURN);
        return getProcessor(getAlipayProperties().getSandbox(), getAlipayProperties().getCertMode(), aliPayProfile);
    }

    public boolean rsaCheckV1(Map<String, String> params, AlipayProfile alipayProfile) {
        try {
            if (getAlipayProperties().getCertMode()) {
                return AlipaySignature.rsaCertCheckV1(params, alipayProfile.getAlipayCertPath(), alipayProfile.getCharset(), alipayProfile.getSignType());
            } else {
                return AlipaySignature.rsaCheckV1(params, alipayProfile.getAlipayPublicKey(), alipayProfile.getCharset(), alipayProfile.getSignType());
            }
        } catch (AlipayApiException e) {
            throw new PaymentSignatureCheckErrorException("Payment signature check catch error.");
        }
    }

    public boolean rsaCheckV2(Map<String, String> params, AlipayProfile alipayProfile) {
        try {
            if (getAlipayProperties().getCertMode()) {
                return AlipaySignature.rsaCertCheckV2(params, alipayProfile.getAlipayCertPath(), alipayProfile.getCharset(), alipayProfile.getSignType());
            } else {
                return AlipaySignature.rsaCheckV2(params, alipayProfile.getAlipayPublicKey(), alipayProfile.getCharset(), alipayProfile.getSignType());
            }
        } catch (AlipayApiException e) {
            throw new PaymentSignatureCheckErrorException("Payment signature check catch error.");
        }
    }

    public String getReturnUrl() {
        return this.getAlipayProperties().getReturnUrl();
    }

    public String getReturnUrl(String tradeNo) {
        return this.getAlipayProperties().getReturnUrl() + SymbolConstants.FORWARD_SLASH + tradeNo;
    }

    public String getNotifyUrl() {
        return this.getAlipayProperties().getNotifyUrl();
    }

    public String getNotifyUrl(String tradeNo) {
        return this.getAlipayProperties().getNotifyUrl() + SymbolConstants.FORWARD_SLASH + tradeNo;
    }

    /**
     * 通过Event，发送支付宝异步通知信息。接收到该Event信息后，可以进行后续的业务处理。
     *
     * @param params 支付宝 notify_url中返回的参数
     */
    public void publishNotifyEvent(Map<String, String> params) {
        if (monocoque) {
            applicationContext.publishEvent(new LocalPaymentNotifyEvent(params));
        } else {
            if (StringUtils.isNotBlank(getAlipayProperties().getDestination())) {
                applicationContext.publishEvent(new RemotePaymentNotifyEvent(JacksonUtils.toJson(params), serviceId, getAlipayProperties().getDestination()));
            }
        }
    }

    /**
     * 通过Event，发送支付宝返回信息。接收到该Event信息后，可以进行后续的业务处理。
     *
     * @param params 支付宝 return_url中返回的参数
     */
    public void publishReturnEvent(Map<String, String> params) {
        if (monocoque) {
            applicationContext.publishEvent(new LocalPaymentReturnEvent(params));
        } else {
            if (StringUtils.isNotBlank(getAlipayProperties().getDestination())) {
                applicationContext.publishEvent(new RemotePaymentReturnEvent(JacksonUtils.toJson(params), serviceId, getAlipayProperties().getDestination()));
            }
        }
    }

    /**
     * 获取支付宝异步通知或者返回信息中的交易号
     *
     * @param params 支付宝 notify_url 或 return_url中返回的参数
     * @return 交易号
     */
    public String getTradeNo(Map<String, String> params) {
        return params.get("out_trade_no");
    }

    /**
     * 支付宝notify_url 或 return_url中返回的信息进行验签，验签通过就将相关信息发送到相关处理逻辑中。
     * <p>
     * 因支付宝异步通知，会通知多次，增加标签进行控制：
     * · 如果标签存在，则认为没有发送过信息。
     * · 如果标签不存在，则认为以及发送过信息，那么就不再进行任何处理。
     * <p>
     * 注：这个逻辑有缺陷，如果缓存出现问题，或者其它前期逻辑有问题，导致标签存储失败则判断不出来。
     * 后续根据逻辑的补充和代码优化，逐步优化此处逻辑。
     *
     * @param params 支付宝 notify_url 或 return_url中返回的参数
     */
    private void feedback(Map<String, String> params, Feedback feedback) {
        String tradeNo = getTradeNo(params);
        String identity = getCache(tradeNo, feedback);

        if (StringUtils.isNotBlank(identity)) {
            AlipayProfile alipayProfile = getProfile(identity);
            boolean verifyResult = rsaCheckV1(params, alipayProfile);
            if (verifyResult) {
                if (feedback == Feedback.PAYMENT_NOTIFY) {
                    publishNotifyEvent(params);
                    deleteCache(tradeNo, Feedback.PAYMENT_NOTIFY);
                } else {
                    publishReturnEvent(params);
                    deleteCache(tradeNo, Feedback.PAYMENT_RETURN);
                }
            }
        }
    }

    public void paymentNotify(Map<String, String> params) {
        feedback(params, Feedback.PAYMENT_NOTIFY);
    }

    public void paymentReturn(Map<String, String> params) {
        feedback(params, Feedback.PAYMENT_RETURN);
    }
}
