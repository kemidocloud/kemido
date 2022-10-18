package com.kemido.sms.netease.annotation;

import com.kemido.sms.netease.configuration.NeteaseSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启网易短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(NeteaseSmsConfiguration.class)
public @interface EnableKemidoNeteaseSms {
}
