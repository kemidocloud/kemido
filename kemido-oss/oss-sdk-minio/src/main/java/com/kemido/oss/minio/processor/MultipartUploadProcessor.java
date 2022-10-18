package com.kemido.oss.minio.processor;

import com.kemido.oss.minio.domain.MultipartUploadCreate;
import com.kemido.oss.minio.service.MultipartUploadAsyncService;
import com.kemido.oss.minio.service.ObjectService;
import io.minio.CreateMultipartUploadResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListPartsResponse;
import io.minio.ObjectWriteResponse;
import io.minio.http.Method;
import io.minio.messages.Part;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: TODO </p>
 */
@Component
public class MultipartUploadProcessor {

    private final MultipartUploadAsyncService multipartUploadAsyncService;
    private final ObjectService objectService;

    @Autowired
    public MultipartUploadProcessor(MultipartUploadAsyncService multipartUploadAsyncService, ObjectService objectService) {
        this.multipartUploadAsyncService = multipartUploadAsyncService;
        this.objectService = objectService;
    }

    private String createUploadId(String bucketName, String objectName) {
        CreateMultipartUploadResponse response = multipartUploadAsyncService.createMultipartUpload(bucketName, objectName);
        return response.result().uploadId();
    }

    private String createPresignedObjectUrl(String bucketName, String objectName, String uploadId, int partNumber) {
        Map<String, String> extraQueryParams = new HashMap<>();
        extraQueryParams.put("partNumber", String.valueOf(partNumber));
        extraQueryParams.put("uploadId", uploadId);

        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .method(Method.PUT)
                .extraQueryParams(extraQueryParams)
                .expiry(1, TimeUnit.HOURS)
                .build();
        return objectService.getPresignedObjectUrl(args);
    }

    private Part[] listParts(String bucketName, String objectName, String uploadId) {
        ListPartsResponse response = multipartUploadAsyncService.listParts(bucketName, objectName, uploadId);
        List<Part> partList = response.result().partList();
        Part[] parts = new Part[partList.size()];
        return partList.toArray(parts);
    }

    public MultipartUploadCreate createMultipartUpload(String bucketName, String objectName, int chunkSize) {
        String uploadId = createUploadId(bucketName, objectName);
        MultipartUploadCreate entity = new MultipartUploadCreate(uploadId);

        for (int i = 0; i < chunkSize; i++) {
            String uploadUrl = createPresignedObjectUrl(bucketName, objectName, uploadId, i);
            entity.appendChunk(uploadUrl);
        }
        return entity;
    }

    public ObjectWriteResponse completeMultipartUpload(String bucketName, String objectName, String uploadId) {
        Part[] parts = listParts(bucketName, objectName, uploadId);
        if (ArrayUtils.isNotEmpty(parts)) {
            return multipartUploadAsyncService.completeMultipartUpload(bucketName, objectName, uploadId, parts);
        }

        return null;
    }
}
