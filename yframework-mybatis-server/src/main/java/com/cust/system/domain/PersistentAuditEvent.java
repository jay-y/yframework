package com.cust.system.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Persist AuditEvent managed by the Spring Boot actuator
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
public class PersistentAuditEvent implements Serializable
{

    private Long id;

    @NotNull
    private String principal;

    private Instant auditEventDate;
    private String auditEventType;

    private Map<String, String> data = new HashMap<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPrincipal()
    {
        return principal;
    }

    public void setPrincipal(String principal)
    {
        this.principal = principal;
    }

    public Instant getAuditEventDate()
    {
        return auditEventDate;
    }

    public void setAuditEventDate(Instant auditEventDate)
    {
        this.auditEventDate = auditEventDate;
    }

    public String getAuditEventType()
    {
        return auditEventType;
    }

    public void setAuditEventType(String auditEventType)
    {
        this.auditEventType = auditEventType;
    }

    public Map<String, String> getData()
    {
        return data;
    }

    public void setData(Map<String, String> data)
    {
        this.data = data;
    }
}
