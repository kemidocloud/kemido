package com.kemido.nosql.couchdb.domain;

import com.kemido.assistant.core.definition.domain.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * <p>Description: CouchDB 基础响应实体 </p>
 */
public class Response implements Entity {

    @JsonProperty("ok")
    private Boolean success = false;

    private String error;

    private String reason;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("success", success)
                .add("error", error)
                .add("reason", reason)
                .toString();
    }
}
