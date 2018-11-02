package org.yframework.ddd.core.domain.model;

import org.yframework.ddd.core.domain.event.IEvent;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 聚合根<br>
 * Comments Name: IAggregateRoot<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IAggregateRoot<ID extends Serializable> extends IEntity<ID>
{
    <Event extends IEvent> Event registerEvent(Event event);

    List<Object> getEvents();

    void clearEvents();
}