package org.yframework.ddd.common.domain.event;

import org.yframework.ddd.common.domain.IDomainObject;

/**
 * Description: 领域事件.<br>
 * Date: 2018/9/29 下午4:00<br>
 * Author: ysj
 */
public interface IDomainEvent extends IDomainObject
{
    long getTimestamp();
}
