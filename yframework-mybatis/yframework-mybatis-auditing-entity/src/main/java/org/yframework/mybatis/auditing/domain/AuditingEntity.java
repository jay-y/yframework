package org.yframework.mybatis.auditing.domain;

import java.io.Serializable;
import java.time.Instant;

/**
 * Description: AudtingEntity.<br>
 * Date: 2017/9/7 10:49<br>
 * Author: ysj
 */
public interface AuditingEntity<ID extends Serializable> extends Serializable
{
    ID getId();

    void setId(ID id);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Instant getCreatedDate();

    void setCreatedDate(Instant createdDate);

    String getLastModifiedBy();

    void setLastModifiedBy(String lastModifiedBy);

    Instant getLastModifiedDate();

    void setLastModifiedDate(Instant lastModifiedDate);

    boolean isActivated();

    void setActivated(boolean activated);
}
