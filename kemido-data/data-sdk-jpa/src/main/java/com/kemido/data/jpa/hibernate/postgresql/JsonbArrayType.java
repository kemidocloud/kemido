package com.kemido.data.jpa.hibernate.postgresql;

import org.hibernate.HibernateException;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * <p>Description: 自定义Jsonb数组类型 </p>
 */
public class JsonbArrayType extends TextArrayType implements Serializable {

    @Override
    protected String dbRealTypeName() {
        return "JSONB";
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x == null) {
            return 0;
        }

        return x.hashCode();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }
}
