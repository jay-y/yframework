package com.cust.system.domain;

import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author zk
 */
@Entity
@Table(name = "sys_office")
public class Office extends AbstractAuditingEntity<String>
{
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "grade")
    private String grade;

    @ManyToOne
    private Office parent;

    public Office()
    {
    }

    public Office(String id)
    {
        super(id);
    }

    public String getName()
    {
        return name;
    }

    public Office name(String name)
    {
        this.name = name;
        return this;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public Office code(String code)
    {
        this.code = code;
        return this;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Integer getSort()
    {
        return sort;
    }

    public Office sort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getType()
    {
        return type;
    }

    public Office type(String type)
    {
        this.type = type;
        return this;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCategory()
    {
        return category;
    }

    public Office category(String category)
    {
        this.category = category;
        return this;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getGrade()
    {
        return grade;
    }

    public Office grade(String grade)
    {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public Office getParent()
    {
        return parent;
    }

    public Office parent(Office office)
    {
        this.parent = office;
        return this;
    }

    public void setParent(Office office)
    {
        this.parent = office;
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
        Office office = (Office) o;
        if (office.getId() == null || getId() == null)
        {
            return false;
        }
        return Objects.equals(getId(), office.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString()
    {
        return "Office{" + "name='" + name + '\'' + ", code='" + code + '\'' + ", sort=" + sort + ", type='" + type + '\'' + ", category='" + category + '\'' + ", grade='" + grade + '\'' + ", parent=" + parent + '}' + super.toString();
    }
}
