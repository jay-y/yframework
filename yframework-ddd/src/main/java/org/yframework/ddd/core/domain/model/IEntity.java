package org.yframework.ddd.core.domain.model;

import java.io.Serializable;

/**
 * Description: 实体<br>
 * Comments Name: IEntity<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IEntity<ID extends Serializable> extends Persistable<ID>
{
}
