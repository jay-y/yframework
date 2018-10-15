package org.yframework.ddd.core.domain.model;

import org.yframework.ddd.core.domain.event.IEvent;

import java.util.List;

/**
 * Description: 聚合根.<br>
 * Date: 2018/9/29 下午4:02<br>
 * Author: ysj
 */
public interface AggregateRoot<ID> extends IEntity<ID>
{
    int getVersion();

    String getName();

    void setName(String name);

    List<IEvent> getEvents();
}
