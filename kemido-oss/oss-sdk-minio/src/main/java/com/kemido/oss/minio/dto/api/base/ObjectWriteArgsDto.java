package com.kemido.oss.minio.dto.api.base;

import com.kemido.assistant.core.utils.DateTimeUtils;
import com.kemido.oss.minio.definition.dto.api.ObjectArgsDto;
import io.minio.ObjectWriteArgs;
import io.minio.messages.Retention;
import io.minio.messages.RetentionMode;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.Map;

/**
 * <p>Description: ObjectWriteDto </p>
 */
public abstract class ObjectWriteArgsDto<B extends ObjectWriteArgs.Builder<B, A>, A extends ObjectWriteArgs> extends ObjectArgsDto<B, A> {

    private Map<String, String> headers;

    private Map<String, String> userMetadata;

    private Map<String, String> tags;

    private RetentionDto retention;

    private Boolean legalHold;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public RetentionDto getRetention() {
        return retention;
    }

    public void setRetention(RetentionDto retention) {
        this.retention = retention;
    }

    public Boolean getLegalHold() {
        return legalHold;
    }

    public void setLegalHold(Boolean legalHold) {
        this.legalHold = legalHold;
    }

    @Override
    protected void prepare(B builder) {
        if (MapUtils.isNotEmpty(getHeaders())) {
            builder.headers(getHeaders());
        }

        if (MapUtils.isNotEmpty(getUserMetadata())) {
            builder.headers(getUserMetadata());
        }

        if (MapUtils.isNotEmpty(getTags())) {
            builder.headers(getTags());
        }

        if (ObjectUtils.isNotEmpty(getRetention())) {
            RetentionMode mode = RetentionMode.valueOf(getRetention().getMode());
            ZonedDateTime retainUntilDate = DateTimeUtils.stringToZonedDateTime(getRetention().getRetainUntilDate());
            builder.retention(new Retention(mode, retainUntilDate));
        }

        super.prepare(builder);
    }

    @Override
    public A build() {
        B builder = getBuilder();
        prepare(builder);
        return builder.build();
    }
}
