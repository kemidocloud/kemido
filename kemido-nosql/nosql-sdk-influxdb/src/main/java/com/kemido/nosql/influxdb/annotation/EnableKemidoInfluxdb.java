package com.kemido.nosql.influxdb.annotation;

import com.kemido.nosql.influxdb.configuration.InfluxdbConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启Influxdb支持 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(InfluxdbConfiguration.class)
public @interface EnableKemidoInfluxdb {

}
