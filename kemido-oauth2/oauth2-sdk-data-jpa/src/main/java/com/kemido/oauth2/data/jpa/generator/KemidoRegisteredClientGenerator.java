package com.kemido.oauth2.data.jpa.generator;

import com.kemido.oauth2.data.jpa.entity.KemidoRegisteredClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * <p>Description: OAuth2RegisteredClient Id 生成器 </p>
 *
 * 指定ID生成器，解决实体ID无法手动设置问题。
 */
public class KemidoRegisteredClientGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (ObjectUtils.isEmpty(object)) {
            throw new HibernateException(new NullPointerException());
        }

        KemidoRegisteredClient oAuthKemidoRegisteredClient = (KemidoRegisteredClient) object;

        if (StringUtils.isEmpty(oAuthKemidoRegisteredClient.getId())) {
            return super.generate(session, object);
        } else {
            return oAuthKemidoRegisteredClient.getId();
        }
    }
}
