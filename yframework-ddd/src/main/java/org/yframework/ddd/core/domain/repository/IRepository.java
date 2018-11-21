package org.yframework.ddd.core.domain.repository;

import java.util.List;

/**
 * Description: 仓储<br>
 * Comments Name: IEntity<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IRepository<T>
{
    List<T> list(Object o);

    int size();

    T get(Object o);

    int add(T t);

    int remove(Object o);

    void clear();
}
