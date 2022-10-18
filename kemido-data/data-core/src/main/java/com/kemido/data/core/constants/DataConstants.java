package com.kemido.data.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: 数据常量 </p>
 */
public interface DataConstants extends BaseConstants {

    String PROPERTY_PREFIX_MULTI_TENANCY = PROPERTY_PREFIX_HERODOTUS + ".multi-tenancy";

    String ITEM_MULTI_TENANCY_ENABLED = PROPERTY_PREFIX_MULTI_TENANCY + PROPERTY_ENABLED;
    String AREA_PREFIX = "data:core:";
    String REGION_MULTI_TENANCY = AREA_PREFIX + "tenancy";

    String ITEM_SPRING_SQL_INIT_PLATFORM = "spring.sql.init.platform";

    String ANNOTATION_SQL_INIT_PLATFORM = ANNOTATION_PREFIX + ITEM_SPRING_SQL_INIT_PLATFORM + ANNOTATION_SUFFIX;
}
