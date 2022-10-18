package com.kemido.web.rest.annotation;

import com.kemido.web.rest.autoconfigure.WebRestAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启Rest注入 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebRestAutoConfiguration.class)
public @interface EnableKemidoRest {
}
