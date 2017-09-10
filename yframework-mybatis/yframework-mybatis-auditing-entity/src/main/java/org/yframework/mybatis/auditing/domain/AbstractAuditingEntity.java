package org.yframework.mybatis.auditing.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;


public abstract class AbstractAuditingEntity<ID extends Serializable> implements AuditingEntity<ID>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private ID id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "activated")
    private boolean activated = true;

    public AbstractAuditingEntity()
    {
    }

    public AbstractAuditingEntity(ID id)
    {
        this.id = id;
    }

    @Override
    public ID getId()
    {
        return id;
    }

    public void setId(ID id)
    {
        this.id = id;
    }

    @Override
    public String getCreatedBy()
    {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    @Override
    public Instant getCreatedDate()
    {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Instant createdDate)
    {
        this.createdDate = createdDate;
    }

    @Override
    public String getLastModifiedBy()
    {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy)
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Instant getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Instant lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean isActivated()
    {
        return activated;
    }

    @Override
    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    @Override
    public String toString()
    {
        return "AbstractAuditingEntity{" + "id='" + id + '\'' + ", createdBy='" + createdBy + '\'' + ", createdDate=" + createdDate + ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate + ", activated=" + activated + '}';
    }
}
