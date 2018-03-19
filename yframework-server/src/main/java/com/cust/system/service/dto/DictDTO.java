package com.cust.system.service.dto;

import com.cust.system.domain.Dict;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;

/**
 * @author zk
 */
public class DictDTO implements AuditingEntityDTO<String>
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String label;

    private String type;

    private String value;

    private String description;

    private boolean activated = true;

    private String remark;

    public DictDTO()
    {
    }

    public DictDTO(String id)
    {
        this.id = id;
    }

    public DictDTO(String id, String label, String type, String value, String description, boolean activated, String remark)
    {
        this.id = id;
        this.label = label;
        this.type = type;
        this.value = value;
        this.description = description;
        this.activated = activated;
        this.remark = remark;
    }

    public DictDTO(Dict dict)
    {
        this(dict.getId(), dict.getLabel(), dict.getType(), dict.getValue(), dict.getDescription(), dict.isActivated(), dict.getRemark());
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

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public String toString()
    {
        return "DictDTO{" + "id='" + id + '\'' + ", label='" + label + '\'' + ", type='" + type + '\'' + ", value='" + value + '\'' + ", description='" + description + '\'' + ", activated=" + activated + ", remark='" + remark + '\'' + '}';
    }
}
