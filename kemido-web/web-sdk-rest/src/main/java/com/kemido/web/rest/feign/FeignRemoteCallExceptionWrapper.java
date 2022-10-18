package com.kemido.web.rest.feign;

import com.kemido.assistant.core.domain.Result;
import com.kemido.assistant.core.exception.PlatformException;

/**
 * <p>Description: Feign Fallback 错误统一封装器 </p>
 */
public class FeignRemoteCallExceptionWrapper extends PlatformException {

    private Result<String> result;

    public FeignRemoteCallExceptionWrapper(Result<String> result) {
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(String message, Result<String> result) {
        super(message);
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(String message, Throwable cause, Result<String> result) {
        super(message, cause);
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(Throwable cause, Result<String> result) {
        super(cause);
        this.result = result;
    }

    public FeignRemoteCallExceptionWrapper(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Result<String> result) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.result = result;
    }

    @Override
    public Result<String> getResult() {
        return result;
    }
}
