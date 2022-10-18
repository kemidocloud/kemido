package com.kemido.oss.minio.domain;

import java.io.Serializable;

/**
 * <p>Description: TODO </p>
 */
public class ObjectInfo implements Serializable {

    private String bucketName;
    private String objectName;
    private String url;
    private String expires;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
