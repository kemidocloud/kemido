package com.kemido.sms.upyun.annotation;

import com.kemido.sms.upyun.configuration.UpyunSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启又拍短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UpyunSmsConfiguration.class)
public @interface EnableKemidoUpyunSms {
}
