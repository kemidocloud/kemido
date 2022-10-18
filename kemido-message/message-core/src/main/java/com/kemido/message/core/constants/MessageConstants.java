package com.kemido.message.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: 消息模块常量 </p>
 */
public interface MessageConstants extends BaseConstants {

    String PROPERTY_PREFIX_QUEUE = PROPERTY_PREFIX_HERODOTUS + ".queue";

    String ITEM_KAFKA_ENABLED = PROPERTY_PREFIX_QUEUE + ".kafka" + PROPERTY_ENABLED;
}
