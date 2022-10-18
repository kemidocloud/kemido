package com.kemido.data.jpa.hibernate.postgresql;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.type.descriptor.sql.LongVarbinaryTypeDescriptor;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import java.sql.Types;

/**
 * <p>Description: 自定义PostgreSQLDialect，增加JSONB等类型支持 </p>
 */
public class KemidoPostgreSQLDialect extends PostgreSQL10Dialect {

    public KemidoPostgreSQLDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
        this.registerColumnType(Types.ARRAY, "text[]");
    }

//    /**
//     * 重载getAddForeignKeyConstraintString方法，阻止ddl-auto添加外键关联
//     *
//     * @param constraintName
//     * @param foreignKey
//     * @param referencedTable
//     * @param primaryKey
//     * @param referencesPrimaryKey
//     * @return String
//     */
//    @Override
//    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
//        //  设置foreignkey对应的列值可以为空
//        return " alter " + foreignKey[0] + " set default null ";
//    }


    /**
     * 在 JPA 环境下，映射 PostgreSQL TEXT 专有类型的三种处理方式中的一种。
     * <p>
     * 这个方法将 @Lob 对应的 CLOB 和 BLOB 合并进行了处理
     *
     * @param sqlTypeDescriptor SQL 类型描述器
     * @return 处理后的 SQL 类型描述器
     * @see <a href="https://www.baeldung.com/jpa-annotation-postgresql-text-type">参考资料1</a>
     * @see <a href="https://blog.csdn.net/weixin_30539835/article/details/98190592">参考资料2</a>
     * @see <a href="https://stackoverflow.com/questions/13090089/org-hibernate-type-texttype-and-oracle">参考资料3</a>
     * @see <a href="https://www.programminghunter.com/article/2482653094/">参考资料4</a>
     */
    @Override
    public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
        switch (sqlTypeDescriptor.getSqlType()) {
            case Types.CLOB:
                return LongVarcharTypeDescriptor.INSTANCE;
            case Types.BLOB:
                return LongVarbinaryTypeDescriptor.INSTANCE;
        }
        return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
    }
}
