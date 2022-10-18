package com.kemido.access.core.constants;

import com.kemido.assistant.core.constants.ErrorCode;

/**
 * <p>Description: Access 模块错误代码 </p>
 */
public interface AccessErrorCode extends ErrorCode {

    int ILLEGAL_ACCESS_SOURCE = ACCESS_MODULE_406_BEGIN + 1;
    int ACCESS_CONFIG_ERROR = ILLEGAL_ACCESS_SOURCE + 1;
}
