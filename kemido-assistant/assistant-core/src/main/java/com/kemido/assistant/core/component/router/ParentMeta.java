package com.kemido.assistant.core.component.router;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Description: 子级节点Meta定义 </p>
 */
public class ParentMeta extends BaseMeta{

    @JsonProperty("isHideAllChild")
    private Boolean hideAllChild = false;

    public Boolean getHideAllChild() {
        return hideAllChild;
    }

    public void setHideAllChild(Boolean hideAllChild) {
        this.hideAllChild = hideAllChild;
    }
}
