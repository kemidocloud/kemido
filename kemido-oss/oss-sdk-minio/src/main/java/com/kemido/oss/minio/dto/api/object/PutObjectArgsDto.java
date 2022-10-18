package com.kemido.oss.minio.dto.api.object;

import com.kemido.oss.minio.dto.api.base.ObjectWriteArgsDto;
import io.minio.PutObjectArgs;

import java.io.InputStream;

/**
 * <p>Description: PutObjectDto </p>
 */
public class PutObjectArgsDto extends ObjectWriteArgsDto<PutObjectArgs.Builder, PutObjectArgs> {

    private InputStream inputStream;
    private Long objectSize;
    private Long partSize;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Long getObjectSize() {
        return objectSize;
    }

    public void setObjectSize(Long objectSize) {
        this.objectSize = objectSize;
    }

    public Long getPartSize() {
        return partSize;
    }

    public void setPartSize(Long partSize) {
        this.partSize = partSize;
    }

    @Override
    protected void prepare(PutObjectArgs.Builder builder) {
        builder.stream(getInputStream(), getObjectSize(), getPartSize());
        super.prepare(builder);
    }

    @Override
    public PutObjectArgs.Builder getBuilder() {
        return PutObjectArgs.builder();
    }
}
