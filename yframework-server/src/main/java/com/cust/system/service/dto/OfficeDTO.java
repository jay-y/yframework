package com.cust.system.service.dto;

import com.cust.system.domain.Office;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;

/**
 * @author zk
 */
public class OfficeDTO implements AuditingEntityDTO<String>
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String code;

    private Integer sort;

    private String type;

    private String category;

    private String grade;

    private Office parent;

    private boolean activated = true;

    public OfficeDTO()
    {
    }

    public OfficeDTO(String id)
    {
        this.id = id;
    }

    public OfficeDTO(String id, String name, String code, Integer sort, String type, String category, String grade, Office parent, boolean activated)
    {
        this.id = id;
        this.name = name;
        this.code = code;
        this.sort = sort;
        this.type = type;
        this.category = category;
        this.grade = grade;
        this.parent = parent;
        this.activated = activated;
    }

    public OfficeDTO(Office office)
    {
        this(office.getId(), office.getName(), office.getCode(), office.getSort(), office.getType(), office.getCategory(), office.getGrade(), office.getParent(), office.isActivated());
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public Office getParent()
    {
        return parent;
    }

    public void setParent(Office parent)
    {
        this.parent = parent;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    @Override
    public String toString()
    {
        return "OfficeDTO{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", code='" + code + '\'' + ", sort=" + sort + ", type='" + type + '\'' + ", category='" + category + '\'' + ", grade='" + grade + '\'' + ", parent=" + parent + ", activated=" + activated + '}';
    }
}
