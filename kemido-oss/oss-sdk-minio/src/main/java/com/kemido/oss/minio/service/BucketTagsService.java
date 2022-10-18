package com.kemido.oss.minio.service;

import com.kemido.oss.core.exception.*;
import com.kemido.oss.minio.definition.service.BaseMinioService;
import io.minio.DeleteBucketTagsArgs;
import io.minio.GetBucketTagsArgs;
import io.minio.MinioClient;
import io.minio.SetBucketTagsArgs;
import io.minio.errors.*;
import io.minio.messages.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * <p>Description: Bucket 标签服务 </p>
 * 当为桶添加标签时，该桶上所有请求产生的计费话单里都会带上这些标签，从而可以针对话单报表做分类筛选，进行更详细的成本分析。例如：某个应用程序在运行过程会往桶里上传数据，我们可以用应用名称作为标签，设置到被使用的桶上。在分析话单时，就可以通过应用名称的标签来分析此应用的成本
 */
@Service
public class BucketTagsService extends BaseMinioService {

    private static final Logger log = LoggerFactory.getLogger(BucketPolicyService.class);

    /**
     * 设置 Bucket 标签
     *
     * @param bucketName bucketName
     * @param tags       {@link Tags}
     */
    public void setBucketTags(String bucketName, Tags tags) {
        setBucketTags(SetBucketTagsArgs.builder().bucket(bucketName).tags(tags).build());
    }

    /**
     * 设置 Bucket 标签
     *
     * @param bucketName bucketName
     * @param region     region
     * @param tags       tags
     */
    public void setBucketTags(String bucketName, String region, Tags tags) {
        setBucketTags(SetBucketTagsArgs.builder().bucket(bucketName).region(region).tags(tags).build());
    }

    /**
     * 设置 Bucket 标签
     *
     * @param bucketName bucketName
     * @param tags       {@link Map}
     */
    public void setBucketTags(String bucketName, Map<String, String> tags) {
        setBucketTags(SetBucketTagsArgs.builder().bucket(bucketName).tags(tags).build());
    }

    /**
     * 设置 Bucket 标签
     *
     * @param bucketName bucketName
     * @param region     region
     * @param tags       {@link Map}
     */
    public void setBucketTags(String bucketName, String region, Map<String, String> tags) {
        setBucketTags(SetBucketTagsArgs.builder().bucket(bucketName).region(region).tags(tags).build());
    }

    /**
     * 设置 Bucket 标签
     *
     * @param setBucketTagsArgs {@link SetBucketTagsArgs}
     */
    public void setBucketTags(SetBucketTagsArgs setBucketTagsArgs) {
        String function = "setBucketTags";
        MinioClient minioClient = getMinioClient();

        try {
            minioClient.setBucketTags(setBucketTagsArgs);
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
     * 获取 Bucket 标签配置
     *
     * @param bucketName bucketName
     * @return {@link Tags}
     */
    public Tags getBucketTags(String bucketName) {
        return getBucketTags(GetBucketTagsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取 Bucket 标签配置
     *
     * @param bucketName bucketName
     * @param region     region
     * @return {@link Tags}
     */
    public Tags getBucketTags(String bucketName, String region) {
        return getBucketTags(GetBucketTagsArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 获取 Bucket 标签配置
     *
     * @param getBucketTagsArgs {@link GetBucketTagsArgs}
     */
    public Tags getBucketTags(GetBucketTagsArgs getBucketTagsArgs) {
        String function = "getBucketTags";
        MinioClient minioClient = getMinioClient();

        try {
            return minioClient.getBucketTags(getBucketTagsArgs);
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
     * 删除 Bucket 标签
     *
     * @param bucketName bucketName
     */
    public void deleteBucketTags(String bucketName) {
        deleteBucketTags(DeleteBucketTagsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 删除 Bucket 标签
     *
     * @param bucketName bucketName
     * @param region     region
     */
    public void deleteBucketTags(String bucketName, String region) {
        deleteBucketTags(DeleteBucketTagsArgs.builder().bucket(bucketName).region(region).build());
    }

    /**
     * 删除 Bucket 标签
     *
     * @param deleteBucketTagsArgs {@link DeleteBucketTagsArgs}
     */
    public void deleteBucketTags(DeleteBucketTagsArgs deleteBucketTagsArgs) {
        String function = "deleteBucketTags";
        MinioClient minioClient = getMinioClient();

        try {
            minioClient.deleteBucketTags(deleteBucketTagsArgs);
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
