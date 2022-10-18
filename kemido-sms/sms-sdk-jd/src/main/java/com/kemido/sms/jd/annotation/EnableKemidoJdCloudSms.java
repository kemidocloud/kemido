package com.kemido.sms.jd.annotation;

import com.kemido.sms.jd.configuration.JdSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启京东云短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JdSmsConfiguration.class)
public @interface EnableKemidoJdCloudSms {
}
