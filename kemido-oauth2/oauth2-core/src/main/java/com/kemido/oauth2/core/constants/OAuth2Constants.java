package com.kemido.oauth2.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: OAuth2 模块通用常量 </p>
 */
public interface OAuth2Constants extends BaseConstants {

    String PROPERTY_PREFIX_OAUTH2 = PROPERTY_PREFIX_HERODOTUS + ".oauth2";
    String PROPERTY_OAUTH2_UI = PROPERTY_PREFIX_OAUTH2 + ".ui";
    String PROPERTY_OAUTH2_COMPLIANCE = PROPERTY_PREFIX_OAUTH2 + ".compliance";
    String ITEM_COMPLIANCE_AUTO_UNLOCK = PROPERTY_OAUTH2_COMPLIANCE + ".auto-unlock";

    String REGION_OAUTH2_AUTHORIZATION = AREA_PREFIX + "oauth2:authorization";
    String REGION_OAUTH2_AUTHORIZATION_CONSENT = AREA_PREFIX + "oauth2:authorization:consent";
    String REGION_OAUTH2_REGISTERED_CLIENT = AREA_PREFIX + "oauth2:registered:client";
    String REGION_OAUTH2_APPLICATION = AREA_PREFIX + "oauth2:application";
    String REGION_OAUTH2_COMPLIANCE = AREA_PREFIX + "oauth2:compliance";
    String REGION_OAUTH2_AUTHORITY = AREA_PREFIX + "oauth2:authority";
    String REGION_OAUTH2_SCOPE = AREA_PREFIX + "oauth2:scope";
    String REGION_OAUTH2_APPLICATION_SCOPE = AREA_PREFIX + "oauth2:application:scope";

    String CACHE_NAME_TOKEN_SIGN_IN_FAILURE_LIMITED = CACHE_TOKEN_BASE_PREFIX + "sign_in:failure_limited:";
    String CACHE_NAME_TOKEN_LOCKED_USER_DETAIL = CACHE_TOKEN_BASE_PREFIX + "locked:user_details:";
}
