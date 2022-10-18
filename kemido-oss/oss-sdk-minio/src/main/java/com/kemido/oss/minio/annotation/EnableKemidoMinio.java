package com.kemido.oss.minio.annotation;

import com.kemido.oss.minio.configuration.MinioConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启 Minio 支持 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MinioConfiguration.class)
public @interface EnableKemidoMinio {
}
