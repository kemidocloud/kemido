package com.kemido.protect.core.constants;

import com.kemido.assistant.core.constants.ErrorCode;

/**
 * <p>Description: Cache 相关错误代码 </p>
 */
public interface ProtectErrorCode extends ErrorCode {

    int SESSION_INVALID = PROTECT_MODULE_406_BEGIN + 1;
    int REPEAT_SUBMISSION = SESSION_INVALID + 1;
    int FREQUENT_REQUESTS = REPEAT_SUBMISSION + 1;
}
