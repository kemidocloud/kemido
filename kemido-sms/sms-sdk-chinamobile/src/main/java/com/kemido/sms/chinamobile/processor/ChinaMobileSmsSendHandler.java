package com.kemido.sms.chinamobile.processor;

import com.kemido.sms.chinamobile.domain.ChinaMobileSmsRequest;
import com.kemido.sms.chinamobile.properties.ChinaMobileSmsProperties;
import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>Description: 移动云发送处理 </p>
 */
public class ChinaMobileSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(ChinaMobileSmsSendHandler.class);

    private final ChinaMobileSmsProperties properties;

    public ChinaMobileSmsSendHandler(ChinaMobileSmsProperties properties) {
        super(properties);
        this.properties = properties;
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.CHINA_MOBILE.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {
        String templateId = this.getTemplateId(template);
        String templateParams = this.getOrderedParamsString(template);

        ChinaMobileSmsRequest request = new ChinaMobileSmsRequest(
                this.properties.getEcName(),
                this.properties.getApId(),
                this.properties.getSecretKey(),
                templateId,
                join(phones),
                templateParams,
                this.properties.getSign());

        HttpResult result = this.http().sync(this.properties.getUri())
                .bodyType(OkHttps.FORM)
                .setBodyPara(request)
                .nothrow()
                .post();

        return result.isSuccessful();
    }
}
