package com.kemido.data.jpa.hibernate.postgresql;

import com.kemido.data.jpa.hibernate.AbstractArrayType;
import org.hibernate.HibernateException;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * <p>Description: 自定义文本数组类型 </p>
 */
public class TextArrayType extends AbstractArrayType<String> implements Serializable {

    @Override
    public Class returnedClass() {
        return String[].class;
    }

    @Override
    protected Object safeReturnedObject() {
        return new String[]{};
    }

    @Override
    protected String dbRealTypeName() {
        return "TEXT";
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
