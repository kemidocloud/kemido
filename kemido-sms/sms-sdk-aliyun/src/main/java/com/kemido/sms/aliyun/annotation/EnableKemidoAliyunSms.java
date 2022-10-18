package com.kemido.sms.aliyun.annotation;

import com.kemido.sms.aliyun.configuration.AliyunSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启阿里云短信</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AliyunSmsConfiguration.class)
public @interface EnableKemidoAliyunSms {
}
