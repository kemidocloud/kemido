package com.kemido.captcha.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: 验证码常量 </p>
 */
public interface CaptchaConstants extends BaseConstants {

    /**
     * security
     */
    String PROPERTY_PREFIX_CAPTCHA = PROPERTY_PREFIX_HERODOTUS + ".captcha";

    String CACHE_NAME_TOKEN_CAPTCHA = CACHE_TOKEN_BASE_PREFIX + "captcha:";

    String CACHE_NAME_CAPTCHA_JIGSAW = CACHE_NAME_TOKEN_CAPTCHA + "jigsaw:";
    String CACHE_NAME_CAPTCHA_WORD_CLICK = CACHE_NAME_TOKEN_CAPTCHA + "word_click:";
    String CACHE_NAME_CAPTCHA_GRAPHIC = CACHE_NAME_TOKEN_CAPTCHA + "graphic:";
}
