package org.yframework.ddd.common.domain;

import java.util.List;

/**
 * Description: 仓储<br>
 * Comments Name: IRepository<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IRepository<O>
{
    List<O> list(Object o);

    int size(Object o);

    O get(Object o);

    int add(O t);

    int remove(Object o);

    void clear(Object o);
}
