package org.yframework.ddd.common.domain;

import java.util.List;

/**
 * Description: 持久化映射器<br>
 * Comments Name: IPersistentMapper<br>
 * Date: 2019/1/3 15:51<br>
 * Author: ysj<br>
 * EditorDate: 2019/1/3 15:51<br>
 * Editor: ysj
 */
public interface IPersistentMapper<T extends IDomainObject>
{
    int deleteByPrimaryKey(Object o);

    int delete(T t);

    int insert(T t);

    int insertSelective(T t);

    boolean existsWithPrimaryKey(Object o);

    List<T> selectAll();

    T selectByPrimaryKey(Object o);

    int selectCount(T t);

    List<T> select(T t);

    T selectOne(T t);

    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);
}
