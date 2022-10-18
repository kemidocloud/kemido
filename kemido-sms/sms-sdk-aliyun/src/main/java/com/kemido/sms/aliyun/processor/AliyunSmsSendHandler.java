package com.kemido.sms.aliyun.processor;

import com.kemido.assistant.core.json.jackson2.utils.JacksonUtils;
import com.kemido.sms.aliyun.properties.AliyunSmsProperties;
import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>Description: 阿里云短信发送处理器 </p>
 */
public class AliyunSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(AliyunSmsSendHandler.class);

    private final IAcsClient iAcsClient;
    private final AliyunSmsProperties properties;

    /**
     * 构造阿里云短信发送处理
     *
     * @param properties 阿里云短信配置
     */
    public AliyunSmsSendHandler(AliyunSmsProperties properties) {
        super(properties);
        this.properties = properties;

        IClientProfile profile = DefaultProfile.getProfile(this.properties.getRegionId(), this.properties.getAccessKeyId(), this.properties.getAccessKeySecret());
        DefaultProfile.addEndpoint(this.properties.getRegionId(), this.properties.getProduct(), this.properties.getDomain());

        iAcsClient = new DefaultAcsClient(profile);
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.ALIYUN.name();
    }

    @Override
    public boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(this.properties.getDomain());
        request.setSysVersion(this.properties.getVersion());
        request.setSysAction(this.properties.getAction());
        request.putQueryParameter("PhoneNumbers", join(phones));
        request.putQueryParameter("SignName", this.properties.getSignName());
        request.putQueryParameter("TemplateCode", this.getTemplateId(template));
        request.putQueryParameter("TemplateParam", JacksonUtils.toJson(template.getParams()));

        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);

            return ObjectUtils.isNotEmpty(response) && response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            log.error("[Kemido] |- [{}] Send Sms Catch Exception: {}", this.getChannel(), e.getMessage());
        }

        return false;
    }
}
