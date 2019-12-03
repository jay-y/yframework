package org.yframework.ddd.common.domain;

import java.util.List;

/**
 * Description: 仓储<br>
 * Comments Name: IRepository<br>
 * Date: 2019/12/3 09:23<br>
 * Author: ysj<br>
 * EditorDate: 2019/12/3 09:23<br>
 * Editor: ysj
 */
public interface IRepository<T extends IDomainObject>
{
    List<T> list(T t);

    int size(T t);

    T get(T t);

    int add(T t);

    int update(T t);

    int remove(T t);
}