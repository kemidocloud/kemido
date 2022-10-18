package com.kemido.protect.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: Protect 模块核心常量 </p>
 */
public interface ProtectConstants extends BaseConstants {

    String PROPERTY_PREFIX_PROTECT = PROPERTY_PREFIX_HERODOTUS + ".protect";
    String PROPERTY_PROTECT_SECURE = PROPERTY_PREFIX_HERODOTUS + ".secure";
    String PROPERTY_PROTECT_CRYPTO = PROPERTY_PREFIX_HERODOTUS + ".crypto";

    String ITEM_PROTECT_CRYPTO_STRATEGY = PROPERTY_PROTECT_CRYPTO + ".crypto-strategy";

    String CACHE_NAME_TOKEN_IDEMPOTENT = CACHE_TOKEN_BASE_PREFIX + "idempotent:";
    String CACHE_NAME_TOKEN_ACCESS_LIMITED = CACHE_TOKEN_BASE_PREFIX + "access_limited:";
    String CACHE_NAME_TOKEN_SECURE_KEY = CACHE_TOKEN_BASE_PREFIX + "secure_key:";

    String ASYMMETRIC_CRYPTO_RSA = "RSA";
    String ASYMMETRIC_CRYPTO_SM2 = "SM2";
    String SYMMETRIC_CRYPTO_AES = "AES";
    String SYMMETRIC_CRYPTO_SM4 = "SM4";
}
