package com.kemido.oss.minio.service;

import com.kemido.oss.core.exception.*;
import com.kemido.oss.minio.definition.service.BaseMinioService;
import io.minio.DeleteObjectLockConfigurationArgs;
import io.minio.GetObjectLockConfigurationArgs;
import io.minio.MinioClient;
import io.minio.SetObjectLockConfigurationArgs;
import io.minio.errors.*;
import io.minio.messages.ObjectLockConfiguration;
import io.minio.messages.RetentionDuration;
import io.minio.messages.RetentionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: Minio 对象锁定配置 </p>
 */
@Service
public class ObjectLockConfigurationService extends BaseMinioService {
    private static final Logger log = LoggerFactory.getLogger(ObjectLockConfigurationService.class);

    /**
     * 设置对象锁定
     *
     * @param bucketName        bucketName
     * @param retentionMode     {@link RetentionMode}
     * @param retentionDuration {@link RetentionDuration}
     */
    public void setObjectLockConfiguration(String bucketName, RetentionMode retentionMode, RetentionDuration retentionDuration) {
        setObjectLockConfiguration(bucketName, new ObjectLockConfiguration(retentionMode, retentionDuration));
    }

    /**
     * 设置对象锁定
     *
     * @param bucketName              bucketName
     * @param objectLockConfiguration {@link ObjectLockConfiguration}
     */
    public void setObjectLockConfiguration(String bucketName, ObjectLockConfiguration objectLockConfiguration) {
        setObjectLockConfiguration(SetObjectLockConfigurationArgs.builder().bucket(bucketName).config(objectLockConfiguration).build());
    }

    /**
     * 设置对象锁定
     *
     * @param bucketName        bucketName
     * @param region            region
     * @param retentionMode     {@link RetentionMode}
     * @param retentionDuration {@link RetentionDuration}
     */
    public void setObjectLockConfiguration(String bucketName, String region, RetentionMode retentionMode, RetentionDuration retentionDuration) {
        setObjectLockConfiguration(bucketName, region, new ObjectLockConfiguration(retentionMode, retentionDuration));
    }

    /**
     * 设置对象锁定
     *
     * @param bucketName              bucketName
     * @param region                  region
     * @param objectLockConfiguration {@link ObjectLockConfiguration}
     */
    public void setObjectLockConfiguration(String bucketName, String region, ObjectLockConfiguration objectLockConfiguration) {
        setObjectLockConfiguration(SetObjectLockConfigurationArgs.builder().bucket(bucketName).region(region).config(objectLockConfiguration).build());
    }

    /**
     * 设置对象锁定
     *
     * @param setObjectLockConfigurationArgs {@link SetObjectLockConfigurationArgs}
     */
    public void setObjectLockConfiguration(SetObjectLockConfigurationArgs setObjectLockConfigurationArgs) {
        String function = "setObjectLockConfiguration";
        MinioClient minioClient = getMinioClient();

        try {
            minioClient.setObjectLockConfiguration(setObjectLockConfigurationArgs);
        } catch (ErrorResponseException e) {
            log.error("[Kemido] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException("Minio response error.");
        } catch (InsufficientDataException e) {
            log.error("[Kemido] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException("Minio insufficient data error.");
        } catch (InternalException e) {
            log.error("[Kemido] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException("Minio internal error.");
        } catch (InvalidKeyException e) {
            log.error("[Kemido] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException("Minio key invalid.");
        } catch (InvalidResponseException e) {
            log.error("[Kemido] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException("Minio response invalid.");
        } catch (IOException e) {
            log.error("[Kemido] |- Minio catch IOException in [{}].", function, e);
            throw new OssIOException("Minio io error.");
        } catch (NoSuchAlgorithmException e) {
            log.error("[Kemido] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException("Minio no such algorithm.");
        } catch (ServerException e) {
            log.error("[Kemido] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException("Minio server error.");
        } catch (XmlParserException e) {
            log.error("[Kemido] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException("Minio xml parser error.");
        } finally {
            close(minioClient);
        }
    }

    /**
     * 获取对象锁定配置
     *
     * @param bucketName bucketName
     * @return {@link ObjectLockConfiguration}
     */
    public ObjectLockConfiguration getObjectLockConfiguration(String bucketName) {
        return getObjectLockConfiguration(GetObjectLockConfigurationArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取对象锁定配置
     *
     * @param bucketName bucketName
     * @param region     region
     * @return {@link ObjectLockConfiguration}
     */
    public ObjectLockConfiguration getObjectLockConfiguration(String bucketName, String region) {
        return getObjectLockConfiguration(GetObjectLockConfigurationArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 获取对象锁定配置
     *
     * @param getObjectLockConfigurationArgs {@link GetObjectLockConfigurationArgs}
     * @return {@link ObjectLockConfiguration}
     */
    public ObjectLockConfiguration getObjectLockConfiguration(GetObjectLockConfigurationArgs getObjectLockConfigurationArgs) {
        String function = "getObjectLockConfiguration";
        MinioClient minioClient = getMinioClient();

        try {
            return minioClient.getObjectLockConfiguration(getObjectLockConfigurationArgs);
        } catch (ErrorResponseException e) {
            log.error("[Kemido] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException("Minio response error.");
        } catch (InsufficientDataException e) {
            log.error("[Kemido] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException("Minio insufficient data error.");
        } catch (InternalException e) {
            log.error("[Kemido] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException("Minio internal error.");
        } catch (InvalidKeyException e) {
            log.error("[Kemido] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException("Minio key invalid.");
        } catch (InvalidResponseException e) {
            log.error("[Kemido] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException("Minio response invalid.");
        } catch (IOException e) {
            log.error("[Kemido] |- Minio catch IOException in [{}].", function, e);
            throw new OssIOException("Minio io error.");
        } catch (NoSuchAlgorithmException e) {
            log.error("[Kemido] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException("Minio no such algorithm.");
        } catch (ServerException e) {
            log.error("[Kemido] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException("Minio server error.");
        } catch (XmlParserException e) {
            log.error("[Kemido] |- Minio catch XmlParserException in createBucket.", e);
            throw new OssXmlParserException("Minio xml parser error.");
        } finally {
            close(minioClient);
        }
    }

    /**
     * 删除对象锁定
     *
     * @param bucketName bucketName
     */
    public void deleteObjectLockConfiguration(String bucketName) {
        deleteObjectLockConfiguration(DeleteObjectLockConfigurationArgs.builder().bucket(bucketName).build());
    }

    /**
     * 删除对象锁定
     *
     * @param bucketName bucketName
     * @param region     region
     */
    public void deleteObjectLockConfiguration(String bucketName, String region) {
        deleteObjectLockConfiguration(DeleteObjectLockConfigurationArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 删除对象锁定
     *
     * @param deleteObjectLockConfigurationArgs {@link DeleteObjectLockConfigurationArgs}
     */
    public void deleteObjectLockConfiguration(DeleteObjectLockConfigurationArgs deleteObjectLockConfigurationArgs) {
        String function = "deleteObjectLockConfiguration";
        MinioClient minioClient = getMinioClient();

        try {
            minioClient.deleteObjectLockConfiguration(deleteObjectLockConfigurationArgs);
        } catch (ErrorResponseException e) {
            log.error("[Kemido] |- Minio catch ErrorResponseException in [{}].", function, e);
            throw new OssErrorResponseException("Minio response error.");
        } catch (InsufficientDataException e) {
            log.error("[Kemido] |- Minio catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException("Minio insufficient data error.");
        } catch (InternalException e) {
            log.error("[Kemido] |- Minio catch InternalException in [{}].", function, e);
            throw new OssInternalException("Minio internal error.");
        } catch (InvalidKeyException e) {
            log.error("[Kemido] |- Minio catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException("Minio key invalid.");
        } catch (InvalidResponseException e) {
            log.error("[Kemido] |- Minio catch InvalidResponseException in [{}].", function, e);
            throw new OssInvalidResponseException("Minio response invalid.");
        } catch (IOException e) {
            log.error("[Kemido] |- Minio catch IOException in [{}].", function, e);
            throw new OssIOException("Minio io error.");
        } catch (NoSuchAlgorithmException e) {
            log.error("[Kemido] |- Minio catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException("Minio no such algorithm.");
        } catch (ServerException e) {
            log.error("[Kemido] |- Minio catch ServerException in [{}].", function, e);
            throw new OssServerException("Minio server error.");
        } catch (XmlParserException e) {
            log.error("[Kemido] |- Minio catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException("Minio xml parser error.");
        } finally {
            close(minioClient);
        }
    }
}
