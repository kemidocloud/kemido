package com.kemido.sms.core.exception;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import com.kemido.sms.core.constants.SmsErrorCode;
import org.apache.http.HttpStatus;

/**
 * <p>Description: Recluse SMS 执行错误 </p>
 */
public class SmsRecluseExecuteException extends PlatformException {

    public SmsRecluseExecuteException() {
        super();
    }

    public SmsRecluseExecuteException(String message) {
        super(message);
    }

    public SmsRecluseExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsRecluseExecuteException(Throwable cause) {
        super(cause);
    }

    protected SmsRecluseExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(SmsErrorCode.SMS_RECLUSE_EXECUTE_ERROR, "短信平台操作错误", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
