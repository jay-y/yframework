package org.yframework.ddd.template.si.persistence.mybatis.adapter;

import org.apache.ibatis.annotations.*;

/**
 * Description: 基本操作资源库(创建、读取、更新、删除).<br>
 * Date: 2018/10/15 上午1:49<br>
 * Author: ysj
 */
public interface CRUDRepositoryAdapter<T>
{
    @InsertProvider(type = CRUDRepositoryAdapterSQLProvider.class, method = CRUDRepositoryAdapterSQLProvider._METHOD_CREATE)
    @Options
    void create(T t);

    @SelectProvider(type = CRUDRepositoryAdapterSQLProvider.class, method = CRUDRepositoryAdapterSQLProvider._METHOD_RETRIEVE)
    T retrieve(T t);

    @UpdateProvider(type = CRUDRepositoryAdapterSQLProvider.class, method = CRUDRepositoryAdapterSQLProvider._METHOD_UPDATE)
    void update(T t);

    @ResultType(value = Boolean.class)
    @SelectProvider(type = CRUDRepositoryAdapterSQLProvider.class, method = CRUDRepositoryAdapterSQLProvider._METHOD_DELETE)
    Boolean delete(T t);
}