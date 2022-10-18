package com.kemido.websocket.accelerator.annotation;

import com.kemido.cache.redisson.annotation.EnableKemidoRedisson;
import com.kemido.websocket.accelerator.configuration.WebSocketConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启 WebSocket </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableKemidoRedisson
@Import(WebSocketConfiguration.class)
public @interface EnableKemidoWebSocket {
}
