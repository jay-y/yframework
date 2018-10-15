package com.cust.system.domain;

import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Dict
 *
 * @author zk
 */
@Entity
@Table(name = "sys_dict")
public class Dict extends AbstractAuditingEntity<String>
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "label", nullable = false)
    private String label;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "description")
    private String description;

    public Dict()
    {
    }

    public Dict(String id)
    {
        super(id);
    }


    public String getLabel()
    {
        return label;
    }

    public Dict label(String label)
    {
        this.label = label;
        return this;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getType()
    {
        return type;
    }

    public Dict type(String type)
    {
        this.type = type;
        return this;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public Dict value(String value)
    {
        this.value = value;
        return this;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public Dict description(String description)
    {
        this.description = description;
        return this;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Dict dict = (Dict) o;
        if (dict.getId() == null || this.getId() == null)
        {
            return false;
        }
        return Objects.equals(this.getId(), dict.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString()
    {
        return "Dict{" + "label='" + label + '\'' + ", type='" + type + '\'' + ", value='" + value + '\'' + ", description='" + description + '\'' + '}' + super.toString();
    }
}
