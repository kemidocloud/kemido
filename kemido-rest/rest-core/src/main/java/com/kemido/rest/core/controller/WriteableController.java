package com.kemido.rest.core.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.assistant.core.definition.domain.AbstractEntity;
import com.kemido.data.core.service.WriteableService;

import java.io.Serializable;

/**
 * <p> Description : 可写Controller </p>
 */
public interface WriteableController<E extends AbstractEntity, ID extends Serializable> extends ReadableController<E, ID> {

    /**
     * 获取Service
     *
     * @return Service
     */
    WriteableService<E, ID> getWriteableService();

    /**
     * 保存或更新实体
     * @param domain 实体参数
     * @return 用Result包装的实体
     */

    default Result<E> saveOrUpdate(E domain) {
        E savedDomain = getWriteableService().saveOrUpdate(domain);
        return result(savedDomain);
    }

    /**
     * 删除数据
     * @param id 实体ID
     * @return 用Result包装的信息
     */
    default Result<String> delete(ID id) {
        Result<String> result = result(String.valueOf(id));
        getWriteableService().deleteById(id);
        return result;
    }
}
