package com.kemido.data.jpa.hibernate.postgresql;


import com.kemido.data.jpa.hibernate.AbstractArrayType;
import org.hibernate.HibernateException;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Description: 自定义数字数组类型 </p>
 */
public class NumericArrayType extends AbstractArrayType<BigDecimal> implements Serializable {

    @Override
    public Class returnedClass() {
        return BigDecimal[].class;
    }

    @Override
    protected Object safeReturnedObject() {
        return new BigDecimal[]{};
    }

    @Override
    protected String dbRealTypeName() {
        return "NUMERIC";
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
