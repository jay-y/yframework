package org.yframework.ddd.domain.support;

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
public interface Auditable<U, ID extends Serializable> extends Persistable<ID>
{
    U getCreatedBy();

    void setCreatedBy(U var1);

    Date getCreatedDate();

    void setCreatedDate(Date var1);

    U getLastModifiedBy();

    void setLastModifiedBy(U var1);

    Date getLastModifiedDate();

    void setLastModifiedDate(Date var1);
}
