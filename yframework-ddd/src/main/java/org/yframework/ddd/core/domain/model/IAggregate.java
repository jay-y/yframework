package org.yframework.ddd.core.domain.model;

import java.io.Serializable;

/**
 * Description: 聚合<br>
 * Comments Name: IAggregate<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IAggregate<ID> extends IAggregateRoot<ID>, Serializable
{
}