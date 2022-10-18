package com.kemido.nosql.couchdb.annotation;

import com.kemido.nosql.couchdb.configuration.CouchdbConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启Influxdb支持 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CouchdbConfiguration.class)
public @interface EnableKemidoCouchdb {

}
