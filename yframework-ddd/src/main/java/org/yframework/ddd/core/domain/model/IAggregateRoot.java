package org.yframework.ddd.core.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 聚合根<br>
 * Comments Name: IAggregateRoot<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IAggregateRoot<ID> extends IEntity<ID>, Serializable
{
    ID getId();

    void setId(ID id);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    String getLastModifiedBy();

    void setLastModifiedBy(String lastModifiedBy);

    Date getLastModifiedDate();

    void setLastModifiedDate(Date lastModifiedDate);
}