package com.kemido.web.core.constants;

import com.kemido.assistant.core.constants.ErrorCode;

/**
 * <p>Description: Web 相关错误代码 </p>
 */
public interface WebErrorCode extends ErrorCode {

    int FEIGN_DECODER_IO_EXCEPTION = WEB_MODULE_503_BEGIN + 1;
}
