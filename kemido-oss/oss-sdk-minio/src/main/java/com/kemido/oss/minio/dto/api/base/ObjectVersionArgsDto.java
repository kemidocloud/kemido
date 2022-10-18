package com.kemido.oss.minio.dto.api.base;

import com.kemido.oss.minio.definition.dto.api.ObjectArgsDto;
import io.minio.ObjectVersionArgs;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: ObjectVersionDto </p>
 */
public abstract class ObjectVersionArgsDto<B extends ObjectVersionArgs.Builder<B, A>, A extends ObjectVersionArgs> extends ObjectArgsDto<B, A> {

    private String versionId;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    @Override
    protected void prepare(B builder) {
        if (StringUtils.isNotEmpty(getVersionId())) {
            builder.versionId(getVersionId());
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
