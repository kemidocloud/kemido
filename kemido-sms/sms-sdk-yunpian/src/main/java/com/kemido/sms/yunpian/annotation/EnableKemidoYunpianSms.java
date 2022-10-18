package com.kemido.sms.yunpian.annotation;

import com.kemido.sms.yunpian.configuration.YunpianSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启云片网短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(YunpianSmsConfiguration.class)
public @interface EnableKemidoYunpianSms {
}
