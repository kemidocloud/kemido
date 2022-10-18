package com.kemido.oss.minio.definition.dto.api;

import com.kemido.oss.minio.definition.MinioArgumentBuilder;
import io.minio.BaseArgs;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * <p>Description: Minio 基础 Dto </p>
 */
public abstract class BaseArgsDto<B extends BaseArgs.Builder<B, A>, A extends BaseArgs> implements MinioArgumentBuilder<B, A> {

    private Map<String, String> extraHeaders;

    private Map<String, String> extraQueryParams;

    public Map<String, String> getExtraHeaders() {
        return extraHeaders;
    }

    public void setExtraHeaders(Map<String, String> extraHeaders) {
        this.extraHeaders = extraHeaders;
    }

    public Map<String, String> getExtraQueryParams() {
        return extraQueryParams;
    }

    public void setExtraQueryParams(Map<String, String> extraQueryParams) {
        this.extraQueryParams = extraQueryParams;
    }

    protected void prepare(B builder) {
        if (MapUtils.isNotEmpty(getExtraHeaders())) {
            builder.extraHeaders(getExtraHeaders());
        }

        if (MapUtils.isNotEmpty(getExtraQueryParams())) {
            builder.extraHeaders(getExtraQueryParams());
        }
    }
}
