package com.kemido.oss.minio.dto.api.base;

import com.kemido.assistant.core.annotation.EnumeratedValue;
import com.kemido.rest.core.definition.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 对象保留设置请求参实体 </p>
 */
public class RetentionDto extends BaseDto {
    @EnumeratedValue(names = {"GOVERNANCE", "COMPLIANCE"}, message = "存储模式的值只能是大写 GOVERNANCE 或者 COMPLIANCE")
    @Schema(name = "对象保留模式", title = "存储模式的值只能是大写 GOVERNANCE 或者 COMPLIANCE")
    private String mode;

    @Schema(name = "保留到日期", title = "对象保留到的日期")
    private String retainUntilDate;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRetainUntilDate() {
        return retainUntilDate;
    }

    public void setRetainUntilDate(String retainUntilDate) {
        this.retainUntilDate = retainUntilDate;
    }
}
