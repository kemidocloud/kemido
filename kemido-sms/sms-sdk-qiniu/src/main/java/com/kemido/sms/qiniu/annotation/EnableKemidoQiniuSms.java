package com.kemido.sms.qiniu.annotation;

import com.kemido.sms.qiniu.configuration.QiniuSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启七牛云短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(QiniuSmsConfiguration.class)
public @interface EnableKemidoQiniuSms {
}
