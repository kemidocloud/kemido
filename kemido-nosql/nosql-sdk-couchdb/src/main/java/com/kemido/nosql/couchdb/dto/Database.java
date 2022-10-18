package com.kemido.nosql.couchdb.dto;

import com.kemido.rest.core.definition.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>Description: 数据库操作请求实体 </p>
 */
@Schema(name = "数据库操作请求实体")
public class Database extends BaseDto {

    @Schema(name = "数据库名称", title = "数据库名称只能是小写字母、数字和 _, $, (, ), +, -, and /.")
    @NotBlank(message = "数据库名称不能为空")
    @Pattern(regexp = "^[a-z][a-z0-9_$()+/-]*$.", message = "数据库名称只能是小写字母、数字和 _, $, (, ), +, -, and /.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
