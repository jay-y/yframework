package org.yframework.ddd.common.domain;

import org.yframework.ddd.common.domain.support.Auditable;
import org.yframework.ddd.common.domain.support.Persistable;

import java.io.Serializable;

/**
 * Description: 持久化对象<br>
 * Comments Name: IPersistentObject<br>
 * Date: 2018/10/16 下午1:59<br>
 * Author: ysj<br>
 * EditorDate: 2018/10/16 下午1:59<br>
 * Editor: ysj
 */
public interface IPersistentObject<U extends Serializable, ID extends Serializable> extends Auditable<U, ID>, Persistable<ID>, Serializable
{
}
