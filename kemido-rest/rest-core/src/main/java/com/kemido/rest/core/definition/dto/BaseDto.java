package com.kemido.rest.core.definition.dto;

import com.kemido.assistant.core.definition.domain.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>Description: DTO基类定义 </p>
 */
public abstract class BaseDto extends AbstractDto {

    @Schema(title = "排序值")
    private Integer ranking = 0;

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
