package org.yframework.ddd.core.domain.model;

/**
 * Description: DomianEntity.<br>
 * Date: 2018/10/7 下午4:02<br>
 * Author: ysj
 */
public interface IEntity<ID>
{
    ID getId();

    void setId(ID id);
}
