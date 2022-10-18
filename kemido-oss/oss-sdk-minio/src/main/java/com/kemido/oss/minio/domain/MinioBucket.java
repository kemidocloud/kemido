package com.kemido.oss.minio.domain;

import com.kemido.assistant.core.definition.domain.Entity;
import com.google.common.base.MoreObjects;

/**
 * <p>Description: Minio Bucket 可序列化实体 </p>
 */
public class MinioBucket implements Entity {

    private String name;

    private String creationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("creationDate", creationDate)
                .toString();
    }
}
