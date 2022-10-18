package com.kemido.oss.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: 对象存储常量 </p>
 */
public interface OssConstants extends BaseConstants {

    String PROPERTY_PREFIX_OSS = PROPERTY_PREFIX_HERODOTUS + ".oss";

    String PROPERTY_OSS_MINIO = PROPERTY_PREFIX_OSS + ".minio";


    String ITEM_MINIO_ENDPOINT = PROPERTY_OSS_MINIO + ".endpoint";
    String ITEM_MINIO_ACCESSKEY = PROPERTY_OSS_MINIO + ".access-key";
    String ITEM_MINIO_SECRETKEY = PROPERTY_OSS_MINIO + ".secret-key";
}
