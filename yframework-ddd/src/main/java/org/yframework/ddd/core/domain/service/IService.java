package org.yframework.ddd.core.domain.service;

import org.yframework.ddd.core.domain.event.IEvent;

/**
 * Description: 领域服务.<br>
 * Date: 2018/9/29 下午4:40<br>
 * Author: ysj
 */
public interface IService
{
    <Event extends IEvent> void handle(Event event);
}