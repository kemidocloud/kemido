package com.kemido.sms.core.definition;

import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: 抽象配置 </p>
 */
public abstract class AbstractSmsProperties implements SmsProperties {

    /**
     * 是否开启
     */
    private Boolean enabled;
    /**
     * 短信模板
     */
    private Map<String, String> templates;

    /**
     * 参数顺序
     */
    private Map<String, List<String>> orders;

    @Override
    public Map<String, String> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, String> templates) {
        this.templates = templates;
    }

    public Map<String, List<String>> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, List<String>> orders) {
        this.orders = orders;
    }

    /**
     * 获取短信模板
     *
     * @param type 类型
     * @return 短信模板
     */
    @Override
    public String getTemplates(String type) {
        if (MapUtils.isNotEmpty(this.templates) && this.templates.containsKey(type)) {
            return this.templates.get(type);
        }
        return null;
    }

    /**
     * 返回参数顺序
     *
     * @param type 类型
     * @return 参数顺序
     */
    @Override
    public List<String> getOrders(String type) {
        if (MapUtils.isNotEmpty(this.orders) && this.orders.containsKey(type)) {
            return this.orders.get(type);
        }
        return null;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
