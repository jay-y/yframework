package org.yframework.ddd.core.domain.model;

import java.io.Serializable;

/**
 * Description: 可持久化的<br>
 * Comments Name: Persistable<br>
 * Date: 2018/11/2 下午3:23<br>
 * Author: ysj<br>
 * EditorDate: 2018/11/2 下午3:23<br>
 * Editor: ysj
 */
public interface Persistable<ID extends Serializable> extends Serializable
{
    ID getId();

    void setId(ID id);

    boolean isNew();

    void setNew(boolean isNew);
}