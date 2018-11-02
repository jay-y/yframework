package org.yframework.ddd.core.domain.repository;

import java.util.List;

/**
 * Description: 仓储.<br>
 * Date: 2018/10/13 下午8:22<br>
 * Author: ysj
 */
public interface IRepository<T, ID>
{
    List<T> list();

    int size();

    T get(ID id);

    boolean add(T t);

    boolean remove(T o);

    void clear();
}
