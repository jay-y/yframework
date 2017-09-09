package org.yframework.mybatis.auditing.service.dto;

import java.io.Serializable;

/**
 * Description: AuditingEntityDTO.<br>
 * Date: 2017/9/8 16:07<br>
 * Author: ysj
 */
public interface AuditingEntityDTO<ID extends Serializable> extends Serializable
{
    ID getId();

    void setId(ID id);
}
