package com.kemido.oauth2.core.definition.strategy;

import com.kemido.oauth2.core.definition.domain.Authority;

import java.util.List;

/**
 * <p>Description: 系统范围服务策略定义 </p>
 */
public interface StrategyAuthorityDetailsService {

    /**
     * 获取全部权限
     * @return 权限集合
     */
    List<Authority> findAll();
}
