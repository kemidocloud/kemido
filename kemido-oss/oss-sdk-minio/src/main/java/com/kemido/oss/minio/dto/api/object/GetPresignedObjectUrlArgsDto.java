package com.kemido.oss.minio.dto.api.object;

import com.kemido.assistant.core.annotation.EnumeratedValue;
import com.kemido.oss.minio.dto.api.base.ObjectVersionArgsDto;
import io.minio.GetPresignedObjectUrlArgs;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: GetPresignedObjectUrlDto </p>
 */
@Schema(name = "预签名对象Url", title = "获取一个指定了 HTTP 方法、到期时间和自定义请求参数的对象URL地址，也就是返回带签名的URL，这个地址可以提供给没有登录的第三方共享访问或者上传对象")
public class GetPresignedObjectUrlArgsDto extends ObjectVersionArgsDto<GetPresignedObjectUrlArgs.Builder, GetPresignedObjectUrlArgs> {

    @EnumeratedValue(names = {"GET", "HEAD", "POST", "PUT", "DELETE"}, message = "预请求对象URL的值只能是大写   GET、HEAD、POST、PUT 和 DELETE")
    @Schema(name = "对象保留模式", title = "存储模式的值只能是大写 GOVERNANCE 或者 COMPLIANCE")
    private String method;
    @Schema(name = "过期时间", type = "integer", title = "单位为秒，默认值为 7 天")
    private Integer expiry = GetPresignedObjectUrlArgs.DEFAULT_EXPIRY_TIME;

    @Override
    public GetPresignedObjectUrlArgs.Builder getBuilder() {
        return GetPresignedObjectUrlArgs.builder();
    }
}
