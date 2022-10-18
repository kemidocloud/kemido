package com.kemido.pay.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: 支付模块常量 </p>
 */
public interface PayConstants extends BaseConstants {

    String PROPERTY_PREFIX_PAY = PROPERTY_PREFIX_HERODOTUS + ".pay";

    String PROPERTY_PAY_ALIPAY = PROPERTY_PREFIX_PAY + ".alipay";
    String PROPERTY_PAY_WXPAY = PROPERTY_PREFIX_PAY + ".wxpay";

    String CACHE_NAME_TOKEN_PAY = CACHE_TOKEN_BASE_PREFIX + "pay:";

    String ITEM_ALIPAY_ENABLED = PROPERTY_PAY_ALIPAY + PROPERTY_ENABLED;
    String ITEM_WXPAY_ENABLED = PROPERTY_PAY_WXPAY + PROPERTY_ENABLED;


}
