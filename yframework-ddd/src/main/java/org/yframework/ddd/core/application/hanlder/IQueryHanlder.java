package org.yframework.ddd.core.application.hanlder;

import java.util.List;

/**
 * Description: 查询处理.<br>
 * Date: 2018/9/29 下午4:05<br>
 * Author: ysj
 */
public interface IQueryHanlder<ID, DTO> extends IHandler
{
    DTO getOne(ID id);

    List<DTO> getAll();
}
