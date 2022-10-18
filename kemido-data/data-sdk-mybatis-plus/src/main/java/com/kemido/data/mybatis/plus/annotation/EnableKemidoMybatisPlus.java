package com.kemido.data.mybatis.plus.annotation;

import com.kemido.data.mybatis.plus.configuration.MybatisPlusConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启 Mybatis Plus 注入 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisPlusConfiguration.class)
public @interface EnableKemidoMybatisPlus {
}
