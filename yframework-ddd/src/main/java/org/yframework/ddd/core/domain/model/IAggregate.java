package org.yframework.ddd.core.domain.model;

import org.yframework.ddd.core.domain.event.IEvent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description: 聚合<br>
 * Comments Name: AggregateRoot<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IAggregate<ID> extends IEntity<ID>, Serializable
{
    ID getId();

    void setId(ID id);

    String getName();

    void setName(String name);

    int getVersion();

    void setVersion(int version);

    List<IEvent> getEvents();

    void setEvents(List<IEvent> events);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    String getLastModifiedBy();

    void setLastModifiedBy(String lastModifiedBy);

    Date getLastModifiedDate();

    void setLastModifiedDate(Date lastModifiedDate);

    Boolean isActivated();

    void setActivated(Boolean activated);
}
