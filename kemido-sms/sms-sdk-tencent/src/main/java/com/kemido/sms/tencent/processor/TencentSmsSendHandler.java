package com.kemido.sms.tencent.processor;

import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import com.kemido.sms.tencent.properties.TencentSmsProperties;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: 腾讯云短信发送处理器 </p>
 */
public class TencentSmsSendHandler extends AbstractSmsSendHandler {

    private static final String SUCCESS_CODE = "OK";

    private final SmsClient sender;
    private final TencentSmsProperties properties;

    public TencentSmsSendHandler(TencentSmsProperties properties) {
        super(properties);
        this.properties = properties;

        Credential credential = new Credential(this.properties.getSecretId(), this.properties.getSecretKey());
        sender = new SmsClient(credential, this.properties.getRegion());
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.TENCENT_CLOUD.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {
        List<List<String>> groups = CollUtil.split(phones, 200);
        String templateId = this.getTemplateId(template);
        List<String> templateParams = this.getOrderedParams(template);
        List<List<String>> errors = groups.parallelStream().map(group -> this.send(templateId, group, templateParams)).collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(errors)) {
            for (List<String> subErrors : errors) {
                result.addAll(subErrors);
            }
        }

        return CollectionUtils.isEmpty(result);
    }

    private List<String> send(String templateId, List<String> mobileGroup, List<String> templateParams) {

        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setSmsSdkAppid(this.properties.getSmsAppId());
            request.setSign(this.properties.getSmsSign());
            request.setTemplateID(templateId);
            request.setTemplateParamSet(ArrayUtil.toArray(templateParams, String.class));
            request.setPhoneNumberSet(ArrayUtil.toArray(mobileGroup, String.class));

            SendSmsResponse sendSmsResponse = sender.SendSms(request);
            if (ArrayUtil.isEmpty(sendSmsResponse.getSendStatusSet())) {
                return mobileGroup;
            } else {
                SendStatus[] sendStatuses = sendSmsResponse.getSendStatusSet();
                return Arrays.stream(sendStatuses)
                        .filter(sendStatus -> !sendStatus.getCode().equals(SUCCESS_CODE))
                        .map(SendStatus::getPhoneNumber).collect(Collectors.toList());
            }
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
