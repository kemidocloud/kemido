package com.kemido.message.core.annotation;

import com.kemido.message.core.constants.MessageConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * <p>Description: 开启传统kafka使用方式支持 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(value = MessageConstants.ITEM_KAFKA_ENABLED)
public @interface ConditionalOnKafkaEnabled {
}
