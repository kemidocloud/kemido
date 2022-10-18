package com.kemido.sms.yunpian.processor;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.sms.core.definition.AbstractSmsSendHandler;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.enums.SmsSupplier;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import com.kemido.sms.yunpian.properties.YunpianSmsProperties;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>Description: 云片网短信发送处理器 </p>
 */
public class YunpianSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(YunpianSmsSendHandler.class);

    private final YunpianClient client;
    private final YunpianSmsProperties properties;

    public YunpianSmsSendHandler(YunpianSmsProperties properties) {
        super(properties);
        this.properties = properties;

        client = new YunpianClient(properties.getApikey()).init();
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.YUNPIAN.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {

        Map<String, String> params = template.getParams();
        List<String> list = new ArrayList<>();

        if (MapUtils.isNotEmpty(params)) {
            params.forEach((key, value) -> {
                list.add(encode(key, value));
            });
        }

        String templateParams = this.join(list, SymbolConstants.AMPERSAND);

        String mobile = join(phones);

        Map<String, String> data = new HashMap<>(8);
        data.put("apikey", properties.getApikey());
        data.put("mobile", mobile);
        data.put("tpl_id", this.getTemplateId(template));
        data.put("tpl_value", templateParams);

        Result<?> result;

        if (StringUtils.contains(mobile, SymbolConstants.COMMA)) {
            result = client.sms().batch_send(data);
        } else {
            result = client.sms().single_send(data);
        }

        return Objects.equals(result.getCode(), 0);
    }

    private String encode(String key, String value) {
        String prefix = encode(SymbolConstants.POUND + key + SymbolConstants.POUND);
        String suffix = encode(value);
        return prefix + SymbolConstants.EQUAL + suffix;
    }

    private String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }
}
