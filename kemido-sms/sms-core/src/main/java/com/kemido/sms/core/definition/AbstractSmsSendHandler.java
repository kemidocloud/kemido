package com.kemido.sms.core.definition;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.sms.core.domain.Template;
import com.kemido.sms.core.exception.ParameterOrdersInvalidException;
import com.kemido.sms.core.exception.TemplateIdInvalidException;
import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.jackson.JacksonMsgConvertor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Description: 抽象短信发送处理器 </p>
 */
public abstract class AbstractSmsSendHandler implements SmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(AbstractSmsSendHandler.class);

    private static final String DEFAULT_NATION_CODE = "+86";

    private final SmsProperties smsProperties;

    public AbstractSmsSendHandler(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    /**
     * 获取具体通道名称
     *
     * @return 通道名称
     */
    protected abstract String getChannel();

    /**
     * 发送短信
     *
     * @param template 通知内容
     * @param phones   手机号列表
     * @return true 发送成功，false发送失败
     * @throws TemplateIdInvalidException      未能找到对应的短信模版ID
     * @throws ParameterOrdersInvalidException 未能找到对应的短信模版参数顺序
     */
    protected abstract boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException;

    @Override
    public boolean send(Template template, List<String> phones) {
        String type = template.getType();

        try {
            boolean isSuccessful = this.execute(template, phones);
            if (isSuccessful) {
                log.debug("[Kemido] |- [{}] ：Send sms is successfully!", this.getChannel());
            } else {
                log.error("[Kemido] |- [{}] ：Send sms is failed!", this.getChannel());
            }
            return isSuccessful;
        } catch (TemplateIdInvalidException e) {
            log.error("[Kemido] |- [{}] : TemplateId [{}] is invalid, Please ensure correct configuration!", this.getChannel(), type);
        } catch (ParameterOrdersInvalidException e) {
            log.error("[Kemido] |- [{}] : Parameter order for template [{}] is invalid, Please ensure correct configuration!", this.getChannel(), type);
        }

        return false;
    }

    protected String getTemplateId(Template template) throws TemplateIdInvalidException {
        String type = template.getType();
        String templateId = smsProperties.getTemplates(type);
        if (StringUtils.isBlank(templateId)) {
            throw new TemplateIdInvalidException("TemplateId " + type + " is invalid");
        } else {
            return templateId;
        }
    }

    private List<String> getOrders(Template template) throws ParameterOrdersInvalidException {
        String type = template.getType();
        List<String> orders = smsProperties.getOrders(type);
        if (CollectionUtils.isEmpty(orders)) {
            throw new ParameterOrdersInvalidException("Parameter order for template " + type + " is invalid");
        } else {
            return orders;
        }
    }

    /**
     * 根据配置的参数顺序，按顺序从传入的参数中取值，
     *
     * @param template 传入的模版相关的参数
     * @return 与配置顺序一致的参数值集合
     * @throws ParameterOrdersInvalidException 模版参数顺序没有配置
     */
    protected List<String> getOrderedParams(Template template) throws ParameterOrdersInvalidException {
        List<String> orders = this.getOrders(template);
        Map<String, String> params = template.getParams();
        return orders.stream().map(params::get).collect(Collectors.toList());
    }

    /**
     * 将SMS参数列表，根据配置的参数顺序， 转换成指定格式的字符串。
     *
     * @param template 传入的模版相关的参数
     * @return 格式的字符串，具体为“["a", "b", "c"]”
     * @throws ParameterOrdersInvalidException 模版参数顺序没有配置
     */
    protected String getOrderedParamsString(Template template) throws ParameterOrdersInvalidException {
        List<String> list = this.getOrderedParams(template);
        return Arrays.toString(list.toArray());
    }

    protected String[] convertPhonesToArray(List<String> phones) {
        String[] array = new String[phones.size()];
        return phones.toArray(array);
    }

    protected String[] convertPhonesToString(List<String> phones) {

        String[] array = new String[phones.size()];
        return phones.toArray(array);
    }

    protected List<String> convertPhonesToList(String phones) {
        String[] result = this.convertPhonesToArray(phones);
        return Arrays.asList(result);
    }

    protected String[] convertPhonesToArray(String phones) {
        return StringUtils.split(phones, SymbolConstants.COMMA);
    }

    /**
     * 将手机号码转换成结构完整的格式
     *
     * @param phone 手机号码
     * @return 结构完整的格式 例如：“+86XXXXXXXXX”
     */
    private String makeWellFormed(String phone, String nationCode) {
        if (StringUtils.startsWith(phone, nationCode)) {
            return phone;
        } else {
            return DEFAULT_NATION_CODE + phone;
        }
    }

    protected String wellFormed(String phones) {
        return this.wellFormed(phones, DEFAULT_NATION_CODE);
    }

    protected String wellFormed(String phones, String nationCode) {
        String[] mobiles = StringUtils.split(phones, SymbolConstants.COMMA);
        return this.wellFormed(Arrays.asList(mobiles), nationCode);
    }

    protected String wellFormed(List<String> phones) {
        return this.wellFormed(phones, DEFAULT_NATION_CODE);
    }

    protected String wellFormed(List<String> phones, String nationCode) {
        List<String> result = phones.stream().map(phone -> this.makeWellFormed(phone, nationCode)).collect(Collectors.toList());
        return join(result);
    }

    protected String join(Collection<String> items) {
        return this.join(items, SymbolConstants.COMMA);
    }

    protected String join(Collection<String> items, String symbol) {
        return StringUtils.join(items, symbol);
    }

    protected HTTP http() {
        return HTTP.builder()
                .addMsgConvertor(new JacksonMsgConvertor())
                .build();
    }
}
