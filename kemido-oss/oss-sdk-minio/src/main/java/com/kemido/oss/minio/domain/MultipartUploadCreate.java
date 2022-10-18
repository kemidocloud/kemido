package com.kemido.oss.minio.domain;

import com.kemido.assistant.core.definition.domain.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 创建分配上传实体 </p>
 */
public class MultipartUploadCreate implements Entity {

    private String uploadId;

    private List<String> chunkUploadUrls;

    public MultipartUploadCreate(String uploadId) {
        this.uploadId = uploadId;
        this.chunkUploadUrls = new ArrayList<>();
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public List<String> getChunkUploadUrls() {
        return chunkUploadUrls;
    }

    public void setChunkUploadUrls(List<String> chunkUploadUrls) {
        this.chunkUploadUrls = chunkUploadUrls;
    }

    public void appendChunk(String chunk) {
        chunkUploadUrls.add(chunkUploadUrls.size(), chunk);
    }
}
