package com.kemido.sms.core.definition;

import com.kemido.sms.core.domain.Template;

import java.util.List;

/**
 * <p>Description: 发送处理 </p>
 */
public interface SmsSendHandler {

    /**
     * 发送短信
     *
     * @param template 短信模版填充相关内容
     * @param phones   手机号码，以“，”分割的多个手机号码字符串
     * @return true 发送成功，false发送失败
     */
    boolean send(Template template, List<String> phones);
}
