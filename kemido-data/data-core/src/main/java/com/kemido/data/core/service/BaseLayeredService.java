package com.kemido.data.core.service;

import com.kemido.assistant.core.constants.SymbolConstants;
import com.kemido.assistant.core.definition.domain.Entity;

import java.io.Serializable;

/**
 * <p>Description: 基于自研Hibernate多层二级缓存的基础服务 </p>
 */
public abstract class BaseLayeredService< E extends Entity, ID extends Serializable> implements WriteableService<E, ID> {

    protected String like(String property) {
        return SymbolConstants.PERCENT + property + SymbolConstants.PERCENT;
    }
}
