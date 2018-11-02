package org.yframework.ddd.core.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 可审计的<br>
 * Comments Name: Auditable<br>
 * Date: 2018/11/2 下午3:23<br>
 * Author: ysj<br>
 * EditorDate: 2018/11/2 下午3:23<br>
 * Editor: ysj
 */
public interface Auditable<T, ID extends Serializable> extends Persistable<ID>
{
    T getCreatedBy();

    void setCreatedBy(T var1);

    Date getCreatedDate();

    void setCreatedDate(Date var1);

    T getLastModifiedBy();

    void setLastModifiedBy(T var1);

    Date getLastModifiedDate();

    void setLastModifiedDate(Date var1);
}