package com.kemido.facility.log.annotation;

import com.kemido.facility.core.constants.FacilityConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * <p>Description: 日志中心是否开启条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(name = FacilityConstants.ITEM_LOG_CENTER_ENABLED)
public @interface ConditionalOnLogEnabled {
}
