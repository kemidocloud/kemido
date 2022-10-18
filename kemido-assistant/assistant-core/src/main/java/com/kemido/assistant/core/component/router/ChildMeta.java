package com.kemido.assistant.core.component.router;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Description: 子节点 Meta </p>
 */
public class ChildMeta extends BaseMeta{

    @JsonProperty("isDetailContent")
    private Boolean detailContent;

    public Boolean getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(Boolean detailContent) {
        this.detailContent = detailContent;
    }
}
