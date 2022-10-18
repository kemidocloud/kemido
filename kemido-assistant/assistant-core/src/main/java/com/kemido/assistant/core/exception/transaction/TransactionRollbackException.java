package com.kemido.assistant.core.exception.transaction;

import com.kemido.assistant.core.domain.Feedback;
import com.kemido.assistant.core.exception.PlatformException;
import org.apache.http.HttpStatus;

/**
 * <p>Description: 事务回滚Exception </p>
 */
public class TransactionRollbackException extends PlatformException {

    public TransactionRollbackException() {
        super();
    }

    public TransactionRollbackException(String message) {
        super(message);
    }

    public TransactionRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionRollbackException(Throwable cause) {
        super(cause);
    }

    protected TransactionRollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return new Feedback(60001, "数据库操作失败，事务回滚", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
