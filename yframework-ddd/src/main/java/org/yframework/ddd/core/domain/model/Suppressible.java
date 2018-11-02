package org.yframework.ddd.core.domain.model;

/**
 * Description: 可隐藏的<br>
 * Comments Name: Suppressible<br>
 * Date: 2018/11/2 下午3:23<br>
 * Author: ysj<br>
 * EditorDate: 2018/11/2 下午3:23<br>
 * Editor: ysj
 */
public interface Suppressible
{
    boolean isDeleted();

    boolean getDeleted();

    void setDeleted(boolean deleted);
}
