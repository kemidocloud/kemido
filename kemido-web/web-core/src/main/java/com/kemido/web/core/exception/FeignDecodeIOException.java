package com.kemido.web.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.web.core.constants.WebErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Feign 解码 IO 错误 </p>
 */
public class FeignDecodeIOException extends PlatformException {

    public FeignDecodeIOException() {
        super();
    }

    public FeignDecodeIOException(String message) {
        super(message);
    }

    public FeignDecodeIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignDecodeIOException(Throwable cause) {
        super(cause);
    }

    protected FeignDecodeIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(WebErrorCode.FEIGN_DECODER_IO_EXCEPTION, "Feign 解析 Fallback 错误信息出错", HttpStatus.SC_SERVICE_UNAVAILABLE);
    }
}
