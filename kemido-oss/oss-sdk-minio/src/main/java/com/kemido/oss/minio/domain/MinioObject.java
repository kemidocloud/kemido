package com.kemido.oss.minio.domain;

import com.google.common.base.MoreObjects;
import io.minio.StatObjectResponse;

import java.util.Date;

/**
 * <p>Description: TODO </p>
 */
public class MinioObject {

    private String bucketName;
    private String name;
    private Date lastModified;
    private long length;
    private String etag;
    private String contentType;

    public MinioObject(String bucketName, String name, Date lastModified, long length, String etag, String contentType) {
        this.bucketName = bucketName;
        this.name = name;
        this.lastModified = lastModified;
        this.length = length;
        this.etag = etag;
        this.contentType = contentType;
    }

    public MinioObject(StatObjectResponse os) {
        this.bucketName = os.bucket();
        this.name = os.object();
        this.lastModified = Date.from(os.lastModified().toInstant());
        this.length = os.size();
        this.etag = os.etag();
        this.contentType = os.contentType();
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bucketName", bucketName)
                .add("name", name)
                .add("lastModified", lastModified)
                .add("length", length)
                .add("etag", etag)
                .add("contentType", contentType)
                .toString();
    }
}
