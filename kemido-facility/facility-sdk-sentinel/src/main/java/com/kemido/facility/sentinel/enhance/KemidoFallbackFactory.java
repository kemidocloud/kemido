package com.kemido.facility.sentinel.enhance;

import feign.Target;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * <p>Description: Feign 统一 Fallback 工厂 </p>
 */
public class KemidoFallbackFactory<T> implements FallbackFactory {

    private final Target<T> target;

    public KemidoFallbackFactory(Target<T> target) {
        this.target = target;
    }

    @Override
    public Object create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new KemidoFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }
}
