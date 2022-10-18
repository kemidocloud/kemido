package com.kemido.rest.core.definition.dto;

import com.kemido.assistant.core.annotation.EnumeratedValue;
import com.kemido.assistant.core.definition.domain.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: 排序参数 </p>
 */
@Schema(title = "排序参数BO对象")
public class Sorter extends AbstractDto {

    @EnumeratedValue(names = {"ASC", "DESC"}, message = "排序方式的值只能是大写 ASC 或者 DESC")
    @Schema(name = "排序方向", title = "排序方向的值只能是大写 ASC 或者 DESC, 默认值：DESC", defaultValue = "DESC")
    private String direction = "DESC";

    @Schema(name = "属性值", title = "指定排序的字段名称")
    private String[] properties;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
