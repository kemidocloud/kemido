package com.kemido.sms.chinamobile.annotation;

import com.kemido.sms.chinamobile.configuration.ChinaMobileSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启移动云短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ChinaMobileSmsConfiguration.class)
public @interface EnableKemidoChinaMobileSms {
}
