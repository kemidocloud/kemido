package com.kemido.sms.jd.processor;

import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import com.kemido.sms.jd.properties.JdSmsProperties;
import com.jdcloud.sdk.auth.CredentialsProvider;
import com.jdcloud.sdk.auth.StaticCredentialsProvider;
import com.jdcloud.sdk.http.HttpRequestConfig;
import com.jdcloud.sdk.http.Protocol;
import com.jdcloud.sdk.service.sms.client.SmsClient;
import com.jdcloud.sdk.service.sms.model.BatchSendRequest;
import com.jdcloud.sdk.service.sms.model.BatchSendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>Description: 京东短信发送处理器 </p>
 */
public class JdSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(JdSmsSendHandler.class);

    private final SmsClient smsClient;
    private final JdSmsProperties properties;

    public JdSmsSendHandler(JdSmsProperties properties) {
        super(properties);
        this.properties = properties;
        CredentialsProvider credentialsProvider = new StaticCredentialsProvider(properties.getAccessKeyId(),
                properties.getSecretAccessKey());
        smsClient = SmsClient.builder().credentialsProvider(credentialsProvider)
                .httpRequestConfig(new HttpRequestConfig.Builder().protocol(Protocol.HTTP).build()).build();
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.JD_CLOUD.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {
        List<String> templateParams = this.getOrderedParams(template);
        String templateId = this.getTemplateId(template);

        BatchSendRequest request = new BatchSendRequest();
        request.setRegionId(this.properties.getRegion());
        request.setTemplateId(templateId);
        request.setSignId(this.properties.getSignId());
        request.setPhoneList(phones);
        request.setParams(templateParams);

        BatchSendResult result = smsClient.batchSend(request).getResult();
        Boolean status = result.getStatus();
        return status != null && status;
    }
}
