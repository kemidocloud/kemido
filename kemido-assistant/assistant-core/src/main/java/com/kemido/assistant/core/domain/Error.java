package com.kemido.assistant.core.domain;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * <p>Description: 错误详情 </p>
 */
@Schema(title = "响应错误详情", description = "为兼容Validation而增加的Validation错误信息实体")
public class Error implements Serializable {

    @Schema(title = "Exception完整信息", type = "string")
    private String detail;

    @Schema(title = "额外的错误信息，目前主要是Validation的Message")
    private String message;

    @Schema(title = "额外的错误代码，目前主要是Validation的Code")
    private String code;

    @Schema(title = "额外的错误字段，目前主要是Validation的Field")
    private String field;

    @Schema(title = "错误堆栈信息")
    private StackTraceElement[] stackTrace;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("detail", detail)
                .add("message", message)
                .add("code", code)
                .add("field", field)
                .toString();
    }
}
