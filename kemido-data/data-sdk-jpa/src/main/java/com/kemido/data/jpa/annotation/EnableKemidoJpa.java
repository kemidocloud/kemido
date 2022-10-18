package com.kemido.data.jpa.annotation;

import com.kemido.data.jpa.configuration.DataJpaConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启阿里云短信</p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DataJpaConfiguration.class)
public @interface EnableKemidoJpa {
}
