package org.yframework.ddd.common.domain;

/**
 * Description: 领域对象<br>
 * Comments Name: IDomainObject<br>
 * Date: 2018/11/2 下午3:23<br>
 * Author: ysj<br>
 * EditorDate: 2018/11/2 下午3:23<br>
 * Editor: ysj
 */
public interface IDomainObject<R extends IPersistentObject>
{
    IDomainObject with(R root);
}