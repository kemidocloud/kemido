package com.kemido.oss.core.constants;

import com.kemido.assistant.core.constants.ErrorCode;

/**
 * <p>Description: 对象存储错误代码 </p>
 */
public interface OssErrorCode extends ErrorCode {

    int OSS_CLIENT_POOL_ERROR = OSS_MODULE_500_BEGIN + 1;
    int OSS_ERROR_RESPONSE = OSS_CLIENT_POOL_ERROR + 1;
    int OSS_INSUFFICIENT_DATA = OSS_ERROR_RESPONSE + 1;
    int OSS_INTERNAL = OSS_INSUFFICIENT_DATA + 1;
    int OSS_INVALID_KEY = OSS_INTERNAL + 1;
    int OSS_INVALID_RESPONSE = OSS_INVALID_KEY + 1;
    int OSS_IO = OSS_INVALID_RESPONSE + 1;
    int OSS_NO_SUCH_ALGORITHM = OSS_IO + 1;
    int OSS_SERVER = OSS_NO_SUCH_ALGORITHM + 1;
    int OSS_XML_PARSER = OSS_SERVER + 1;
    int OSS_EXECUTION = OSS_XML_PARSER + 1;
    int OSS_INTERRUPTED = OSS_EXECUTION + 1;
}
