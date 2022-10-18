package com.kemido.cache.core.constants;

import com.kemido.assistant.core.constants.ErrorCode;

/**
 * <p>Description: Cache 相关错误代码 </p>
 */
public interface CacheErrorCode extends ErrorCode {

    int STAMP_DELETE_FAILED = CACHE_MODULE_406_BEGIN + 1;
    int STAMP_HAS_EXPIRED = STAMP_DELETE_FAILED + 1;
    int STAMP_MISMATCH = STAMP_HAS_EXPIRED + 1;
    int STAMP_PARAMETER_ILLEGAL = STAMP_MISMATCH + 1;
}
