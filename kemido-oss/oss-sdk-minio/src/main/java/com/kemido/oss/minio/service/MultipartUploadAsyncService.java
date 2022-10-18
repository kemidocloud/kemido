package com.kemido.oss.minio.service;

import com.kemido.oss.core.exception.*;
import com.kemido.oss.minio.core.MinioAsyncClient;
import com.kemido.oss.minio.definition.service.BaseMinioAsyncService;
import com.google.common.collect.Multimap;
import io.minio.CreateMultipartUploadResponse;
import io.minio.ListPartsResponse;
import io.minio.ObjectWriteResponse;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

/**
 * <p>Description: 分片上传服务 </p>
 */
@Service
public class MultipartUploadAsyncService extends BaseMinioAsyncService {

    private static final Logger log = LoggerFactory.getLogger(MultipartUploadAsyncService.class);

    /**
     * 创建分片上传请求
     *
     * @param bucketName 存储桶
     * @param objectName 对象名
     * @return {@link CreateMultipartUploadResponse}
     */
    public CreateMultipartUploadResponse createMultipartUpload(String bucketName, String objectName) {
        return createMultipartUpload(bucketName, null, objectName);
    }

    /**
     * 创建分片上传请求
     *
     * @param bucketName 存储桶
     * @param region     区域
     * @param objectName 对象名
     * @return {@link CreateMultipartUploadResponse}
     */
    public CreateMultipartUploadResponse createMultipartUpload(String bucketName, String region, String objectName) {
        return createMultipartUpload(bucketName, region, objectName, null);
    }

    /**
     * 创建分片上传请求
     *
     * @param bucketName   存储桶
     * @param region       区域
     * @param objectName   对象名
     * @param extraHeaders 消息头
     * @return {@link CreateMultipartUploadResponse}
     */
    public CreateMultipartUploadResponse createMultipartUpload(String bucketName, String region, String objectName, Multimap<String, String> extraHeaders) {
        return createMultipartUpload(bucketName, region, objectName, extraHeaders, null);
    }

    /**
     * 创建分片上传请求
     *
     * @param bucketName       存储桶
     * @param region           区域
     * @param objectName       对象名
     * @param extraHeaders     额外消息头
     * @param extraQueryParams 额外查询参数
     * @return {@link CreateMultipartUploadResponse}
     */
    public CreateMultipartUploadResponse createMultipartUpload(String bucketName, String region, String objectName, Multimap<String, String> extraHeaders, Multimap<String, String> extraQueryParams) {
        String function = "createMultipartUpload";
        MinioAsyncClient minioAsyncClient = getMinioClient();

        try {
            return minioAsyncClient.createMultipartUploadAsync(bucketName, region, objectName, extraHeaders, extraQueryParams).get();
        } catch (InsufficientDataException e) {
            log.error("[Kemido] |- Minio async catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException("Minio async insufficient data error.");
        } catch (InternalException e) {
            log.error("[Kemido] |- Minio async catch InternalException in [{}].", function, e);
            throw new OssInternalException("Minio async internal error.");
        } catch (InvalidKeyException e) {
            log.error("[Kemido] |- Minio async catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException("Minio async key invalid.");
        } catch (IOException e) {
            log.error("[Kemido] |- Minio async catch IOException in [{}].", function, e);
            throw new OssIOException("Minio async io error.");
        } catch (NoSuchAlgorithmException e) {
            log.error("[Kemido] |- Minio async catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException("Minio async no such algorithm.");
        } catch (XmlParserException e) {
            log.error("[Kemido] |- Minio async catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException("Minio async xml parser error.");
        } catch (ExecutionException e) {
            log.error("[Kemido] |- Minio async catch ExecutionException in [{}].", function, e);
            throw new OssExecutionException("Minio async execution error.");
        } catch (InterruptedException e) {
            log.error("[Kemido] |- Minio async catch InterruptedException in [{}].", function, e);
            throw new OssInterruptedException("Minio async interrupted error.");
        } finally {
            close(minioAsyncClient);
        }
    }

    /**
     * 完成分片上传，执行合并文件
     *
     * @param bucketName 存储桶
     * @param objectName 对象名
     * @param uploadId   上传ID
     * @param parts      {@link Part}
     * @return {@link ObjectWriteResponse}
     */
    public ObjectWriteResponse completeMultipartUpload(String bucketName, String objectName, String uploadId, Part[] parts) {
        return completeMultipartUpload(bucketName, null, objectName, uploadId, parts);
    }

    /**
     * 完成分片上传，执行合并文件
     *
     * @param bucketName 存储桶
     * @param region     区域
     * @param objectName 对象名
     * @param uploadId   上传ID
     * @return {@link ObjectWriteResponse}
     */
    public ObjectWriteResponse completeMultipartUpload(String bucketName, String region, String objectName, String uploadId) {
        return completeMultipartUpload(bucketName, region, objectName, uploadId, null);
    }

    /**
     * 完成分片上传，执行合并文件
     *
     * @param bucketName 存储桶
     * @param region     区域
     * @param objectName 对象名
     * @param uploadId   上传ID
     * @param parts      {@link Part}
     * @return {@link ObjectWriteResponse}
     */
    public ObjectWriteResponse completeMultipartUpload(String bucketName, String region, String objectName, String uploadId, Part[] parts) {
        return completeMultipartUpload(bucketName, region, objectName, uploadId, parts, null);
    }

    /**
     * 完成分片上传，执行合并文件
     *
     * @param bucketName   存储桶
     * @param region       区域
     * @param objectName   对象名
     * @param uploadId     上传ID
     * @param parts        {@link Part}
     * @param extraHeaders 额外消息头
     * @return {@link ObjectWriteResponse}
     */
    public ObjectWriteResponse completeMultipartUpload(String bucketName, String region, String objectName, String uploadId, Part[] parts, Multimap<String, String> extraHeaders) {
        return completeMultipartUpload(bucketName, region, objectName, uploadId, parts, extraHeaders, null);
    }

    /**
     * 完成分片上传，执行合并文件
     *
     * @param bucketName       存储桶
     * @param region           区域
     * @param objectName       对象名
     * @param uploadId         上传ID
     * @param parts            {@link Part}
     * @param extraHeaders     额外消息头
     * @param extraQueryParams 额外查询参数
     * @return {@link ObjectWriteResponse}
     */
    public ObjectWriteResponse completeMultipartUpload(String bucketName, String region, String objectName, String uploadId, Part[] parts, Multimap<String, String> extraHeaders, Multimap<String, String> extraQueryParams) {
        String function = "completeMultipartUploadAsync";
        MinioAsyncClient minioAsyncClient = getMinioClient();

        try {
            return minioAsyncClient.completeMultipartUploadAsync(bucketName, region, objectName, uploadId, parts, extraHeaders, extraQueryParams).get();
        } catch (InsufficientDataException e) {
            log.error("[Kemido] |- Minio async catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException("Minio async insufficient data error.");
        } catch (InternalException e) {
            log.error("[Kemido] |- Minio async catch InternalException in [{}].", function, e);
            throw new OssInternalException("Minio async internal error.");
        } catch (InvalidKeyException e) {
            log.error("[Kemido] |- Minio async catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException("Minio async key invalid.");
        } catch (IOException e) {
            log.error("[Kemido] |- Minio async catch IOException in [{}].", function, e);
            throw new OssIOException("Minio async io error.");
        } catch (NoSuchAlgorithmException e) {
            log.error("[Kemido] |- Minio async catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException("Minio async no such algorithm.");
        } catch (XmlParserException e) {
            log.error("[Kemido] |- Minio async catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException("Minio async xml parser error.");
        } catch (ExecutionException e) {
            log.error("[Kemido] |- Minio async catch ExecutionException in [{}].", function, e);
            throw new OssExecutionException("Minio async execution error.");
        } catch (InterruptedException e) {
            log.error("[Kemido] |- Minio async catch InterruptedException in [{}].", function, e);
            throw new OssInterruptedException("Minio async interrupted error.");
        } finally {
            close(minioAsyncClient);
        }
    }

    /**
     * 查询分片数据
     *
     * @param bucketName 存储桶
     * @param objectName 对象名
     * @param uploadId   上传ID
     * @return {@link ListPartsResponse}
     */
    public ListPartsResponse listParts(String bucketName, String objectName, String uploadId) {
        return listParts(bucketName, null, objectName, uploadId);
    }

    /**
     * 查询分片数据
     *
     * @param bucketName 存储桶
     * @param region     区域
     * @param objectName 对象名
     * @param uploadId   上传ID
     * @return {@link ListPartsResponse}
     */
    public ListPartsResponse listParts(String bucketName, String region, String objectName, String uploadId) {
        return listParts(bucketName, region, objectName, null, uploadId);
    }

    /**
     * 查询分片数据
     *
     * @param bucketName 存储桶
     * @param region     区域
     * @param objectName 对象名
     * @param maxParts   抓取的最大分片数量.
     * @param uploadId   上传ID
     * @return {@link ListPartsResponse}
     */
    public ListPartsResponse listParts(String bucketName, String region, String objectName, Integer maxParts, String uploadId) {
        return listParts(bucketName, region, objectName, maxParts, null, uploadId);
    }

    /**
     * 查询分片数据
     *
     * @param bucketName       存储桶
     * @param region           区域
     * @param objectName       对象名
     * @param maxParts         抓取的最大分片数量.
     * @param partNumberMarker 分片数量创建器.
     * @param uploadId         上传ID
     * @return {@link ListPartsResponse}
     */
    public ListPartsResponse listParts(String bucketName, String region, String objectName, Integer maxParts, Integer partNumberMarker, String uploadId) {
        return listParts(bucketName, region, objectName, maxParts, partNumberMarker, uploadId, null);
    }

    /**
     * 查询分片数据
     *
     * @param bucketName       存储桶
     * @param region           区域
     * @param objectName       对象名
     * @param maxParts         抓取的最大分片数量.
     * @param partNumberMarker 分片数量创建器.
     * @param uploadId         上传ID
     * @param extraHeaders     额外消息头
     * @return {@link ListPartsResponse}
     */
    public ListPartsResponse listParts(String bucketName, String region, String objectName, Integer maxParts, Integer partNumberMarker, String uploadId, Multimap<String, String> extraHeaders) {
        return listParts(bucketName, region, objectName, maxParts, partNumberMarker, uploadId, extraHeaders, null);
    }

    /**
     * 查询分片数据
     *
     * @param bucketName       存储桶
     * @param region           区域
     * @param objectName       对象名
     * @param maxParts         抓取的最大分片数量.
     * @param partNumberMarker 分片数量创建器.
     * @param uploadId         上传ID
     * @param extraHeaders     额外消息头
     * @param extraQueryParams 额外查询参数
     * @return {@link ListPartsResponse}
     */
    public ListPartsResponse listParts(String bucketName, String region, String objectName, Integer maxParts, Integer partNumberMarker, String uploadId, Multimap<String, String> extraHeaders, Multimap<String, String> extraQueryParams) {
        String function = "listParts";
        MinioAsyncClient minioAsyncClient = getMinioClient();

        try {
            return minioAsyncClient.listPartsAsync(bucketName, region, objectName, maxParts, partNumberMarker, uploadId, extraHeaders, extraQueryParams).get();
        } catch (InsufficientDataException e) {
            log.error("[Kemido] |- Minio async catch InsufficientDataException in [{}].", function, e);
            throw new OssInsufficientDataException("Minio async insufficient data error.");
        } catch (InternalException e) {
            log.error("[Kemido] |- Minio async catch InternalException in [{}].", function, e);
            throw new OssInternalException("Minio async internal error.");
        } catch (InvalidKeyException e) {
            log.error("[Kemido] |- Minio async catch InvalidKeyException in [{}].", function, e);
            throw new OssInvalidKeyException("Minio async key invalid.");
        } catch (IOException e) {
            log.error("[Kemido] |- Minio async catch IOException in [{}].", function, e);
            throw new OssIOException("Minio async io error.");
        } catch (NoSuchAlgorithmException e) {
            log.error("[Kemido] |- Minio async catch NoSuchAlgorithmException in [{}].", function, e);
            throw new OssNoSuchAlgorithmException("Minio async no such algorithm.");
        } catch (XmlParserException e) {
            log.error("[Kemido] |- Minio async catch XmlParserException in [{}].", function, e);
            throw new OssXmlParserException("Minio async xml parser error.");
        } catch (ExecutionException e) {
            log.error("[Kemido] |- Minio async catch ExecutionException in [{}].", function, e);
            throw new OssExecutionException("Minio async execution error.");
        } catch (InterruptedException e) {
            log.error("[Kemido] |- Minio async catch InterruptedException in [{}].", function, e);
            throw new OssInterruptedException("Minio async interrupted error.");
        } finally {
            close(minioAsyncClient);
        }
    }
}
