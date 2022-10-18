package com.kemido.sms.netease.processor;

import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import com.kemido.sms.netease.domain.NeteaseSmsResponse;
import com.kemido.sms.netease.properties.NeteaseSmsProperties;
import cn.hutool.core.util.RandomUtil;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * <p>Description: 网易云短信发送处理器 </p>
 */
public class NeteaseSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(NeteaseSmsSendHandler.class);

    /**
     * 请求路径URL
     */
    private final NeteaseSmsProperties properties;

    public NeteaseSmsSendHandler(NeteaseSmsProperties properties) {
        super(properties);
        this.properties = properties;
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.NETEASE_CLOUD.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {

        String templateId = this.getTemplateId(template);
        String templateParams = this.getOrderedParamsString(template);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String nonce = RandomUtil.randomString(6);
        String checkSum = CheckSumBuilder.getCheckSum(this.properties.getAppSecret(), nonce, curTime);

        HttpResult result = this.http().sync(this.properties.getApiUrl())
                .bodyType(OkHttps.FORM)
                .addHeader("AppKey", this.properties.getAppKey())
                .addHeader("CurTime", curTime)
                .addHeader("CheckSum", checkSum)
                .addHeader("Nonce", nonce)
                .addBodyPara("templateid", templateId)
                .addBodyPara("mobiles", join(phones))
                .addBodyPara("params", templateParams)
                .nothrow()
                .post();

        if (result.isSuccessful()) {
            NeteaseSmsResponse neteaseSmsResponse = result.getBody().toBean(NeteaseSmsResponse.class);
            return ObjectUtils.isNotEmpty(neteaseSmsResponse) && NeteaseSmsResponse.SUCCESS_CODE.equals(neteaseSmsResponse.getCode());
        }

        return false;
    }
}
