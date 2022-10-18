package com.kemido.web.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: Web包属性常量 </p>
 */
public interface WebConstants extends BaseConstants {

    String PROPERTY_PREFIX_ENDPOINT = PROPERTY_PREFIX_HERODOTUS + ".endpoint";

    String PROPERTY_FEIGN_OKHTTP = PROPERTY_PREFIX_FEIGN + ".okhttp";
    String PROPERTY_FEIGN_HTTPCLIENT = PROPERTY_PREFIX_FEIGN + ".httpclient";
    String PROPERTY_REST_SCAN = PROPERTY_PREFIX_REST + ".scan";


    /* ---------- Kemido 详细配置属性路径 ---------- */

    String ITEM_SCAN_ENABLED = PROPERTY_REST_SCAN + PROPERTY_ENABLED;
    String ITEM_FEIGN_OKHTTP_ENABLED = PROPERTY_FEIGN_OKHTTP + PROPERTY_ENABLED;
    String ITEM_FEIGN_HTTPCLIENT_ENABLED = PROPERTY_FEIGN_HTTPCLIENT + PROPERTY_ENABLED;

}
