package com.kemido.facility.sentinel.enhance;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.assistant.core.domain.Result;
import com.kemido.assistant.core.exception.GlobalExceptionHandler;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Description: 统一 fallback 实体 </p>
 */
public class KemidoFallback<T> implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(KemidoFallback.class);

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    public KemidoFallback(Class<T> targetType, String targetName, Throwable cause) {
        this.targetType = targetType;
        this.targetName = targetName;
        this.cause = cause;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        String errorMessage = cause.getMessage();
        String path = targetType.getName() + SymbolConstants.FORWARD_SLASH + method.getName();

        Result<String> result = GlobalExceptionHandler.resolveException((Exception) cause, path);
        log.error("[Kemido] |- Feign remote call fallback : [{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName, errorMessage);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KemidoFallback<?> that = (KemidoFallback<?>) o;
        return Objects.equal(targetType, that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(targetType);
    }
}
