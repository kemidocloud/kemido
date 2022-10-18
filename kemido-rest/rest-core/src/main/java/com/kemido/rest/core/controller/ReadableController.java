package com.kemido.rest.core.controller;

import com.kemido.assistant.core.domain.Result;
import com.kemido.assistant.core.definition.domain.AbstractEntity;
import com.kemido.data.core.service.ReadableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 只读Controller </p>
 */
public interface ReadableController<E extends AbstractEntity, ID extends Serializable> extends Controller {

    /**
     * 获取Service
     *
     * @return Service
     */
    ReadableService<E, ID> getReadableService();

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码，起始页码 0
     * @param pageSize   每页显示数据条数
     * @return {@link Result}
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize) {
        Page<E> pages = getReadableService().findByPage(pageNumber, pageSize);
        return result(pages);
    }

    /**
     * 查询分页数据
     *
     * @param pageNumber 当前页码, 起始页码 0
     * @param pageSize   每页显示的数据条数
     * @param direction  {@link org.springframework.data.domain.Sort.Direction}
     * @param properties 排序的属性名称
     * @return 分页数据
     */
    default Result<Map<String, Object>> findByPage(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<E> pages = getReadableService().findByPage(pageNumber, pageSize, direction, properties);
        return result(pages);
    }

    default Result<List<E>> findAll() {
        List<E> domains = getReadableService().findAll();
        return result(domains);
    }

    default Result<E> findById(ID id) {
        E domain = getReadableService().findById(id);
        return result(domain);
    }
}
