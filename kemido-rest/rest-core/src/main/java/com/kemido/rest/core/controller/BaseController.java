package com.kemido.rest.core.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.assistant.core.definition.domain.AbstractEntity;
import com.kemido.data.core.service.ReadableService;

import java.io.Serializable;

/**
 * <p> Description : 通用Controller </p>
 * <p>
 * 单独提取出一些公共方法，是为了解决某些支持feign的controller，requestMapping 不方便统一编写的问题。
 */
public abstract class BaseController<E extends AbstractEntity, ID extends Serializable> implements WriteableController<E, ID> {

    /**
     * 获取Service
     *
     * @return Service
     */
    @Override
    public ReadableService<E, ID> getReadableService() {
        return this.getWriteableService();
    }

    @Override
    public Result<E> saveOrUpdate(E domain) {
        E savedDomain = getWriteableService().saveOrUpdate(domain);
        return result(savedDomain);
    }

    @Override
    public Result<String> delete(ID id) {
        Result<String> result = result(String.valueOf(id));
        getWriteableService().deleteById(id);
        return result;
    }
}
