package com.kemido.sms.core.definition;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: 短信属性定义 </p>
 */
public interface SmsProperties {

    /**
     * 获取短信模版
     *
     * @return 短信模版
     */
    Map<String, String> getTemplates();

    /**
     * 根据类别获取短信模版
     *
     * @param type 模版类别
     * @return 与模版类别对应的短信模版
     */
    String getTemplates(String type);

    /**
     * 短信模版顺序定义
     *
     * @param type 模版类别
     * @return 与模版类别对应的顺序定义
     */
    List<String> getOrders(String type);
}
