package com.kemido.oauth2.core.definition.service;

import com.kemido.oauth2.core.definition.domain.KemidoGrantedAuthority;

import java.util.Set;

/**
 * <p>Description: 客户端操作基础接口 </p>
 */
public interface ClientDetailsService {

    /**
     * 根据客户端ID获取客户端权限
     *
     * @param clientId 客户端ID
     * @return 客户端权限集合
     */
    Set<KemidoGrantedAuthority> findAuthoritiesById(String clientId);
}
