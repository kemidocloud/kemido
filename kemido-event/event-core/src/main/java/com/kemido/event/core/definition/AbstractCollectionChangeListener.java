package com.kemido.event.core.definition;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 实体集合属性变更监听器 </p>
 */
public abstract class AbstractCollectionChangeListener extends AbstractEntityListener {

    private List<String> before;
    private List<String> after;

    public void setBefore(List<String> before) {
        this.before = before;
    }

    public void setAfter(List<String> after) {
        this.after = after;
    }

    protected List<String> getChangedItems() {
        if (CollectionUtils.isNotEmpty(this.before) && CollectionUtils.isNotEmpty(this.after)) {
            return new ArrayList<>(CollectionUtils.disjunction(this.before, this.after));
        }

        if (CollectionUtils.isNotEmpty(this.before) && CollectionUtils.isEmpty(this.after)) {
            return this.before;
        }

        if (CollectionUtils.isEmpty(this.before) && CollectionUtils.isNotEmpty(this.after)) {
            return this.after;
        }

        return new ArrayList<>();
    }
}
