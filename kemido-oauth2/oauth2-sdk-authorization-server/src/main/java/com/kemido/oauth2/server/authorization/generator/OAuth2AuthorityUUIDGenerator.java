package com.kemido.oauth2.server.authorization.generator;

import com.kemido.oauth2.server.authorization.entity.OAuth2Authority;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * <p>Description: 使得保存实体类时可以在保留主键生成策略的情况下自定义表的主键 </p>
 */
public class OAuth2AuthorityUUIDGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        OAuth2Authority authority = (OAuth2Authority) object;

        if (StringUtils.isEmpty(authority.getAuthorityId())) {
            return super.generate(session, object);
        } else {
            return authority.getAuthorityId();
        }
    }
}

