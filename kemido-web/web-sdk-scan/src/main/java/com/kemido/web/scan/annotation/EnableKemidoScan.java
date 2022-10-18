package com.kemido.web.scan.annotation;

import com.kemido.web.scan.configuration.ScanConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启Scan注入 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ScanConfiguration.class)
public @interface EnableKemidoScan {
}
