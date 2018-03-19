package com.cust.system.service.dto;

import com.cust.system.domain.Role;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zk
 */
public class RoleDTO implements AuditingEntityDTO<String>
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String nameen;

    private String scope;

    private Integer sort;

    private Boolean isSys;

    private List<String> menus = new ArrayList<>();

    private String officeId;

    private boolean activated = true;

    public RoleDTO()
    {
    }

    public RoleDTO(String id)
    {
        this.id = id;
    }

    public RoleDTO(String id, String name, String nameen, String scope, Integer sort, Boolean isSys, String officeId, boolean activated)
    {
        this.id = id;
        this.name = name;
        this.nameen = nameen;
        this.scope = scope;
        this.sort = sort;
        this.isSys = isSys;
        this.officeId = officeId;
        this.activated = activated;
    }

    public RoleDTO(Role role)
    {
        this(role.getId(), role.getName(), role.getNameen(), role.getScope(), role.getSort(), role.isSys(), role.getOfficeId(), role.isActivated());
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

    public String getNameen()
    {
        return nameen;
    }

    public void setNameen(String nameen)
    {
        this.nameen = nameen;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Boolean isSys()
    {
        return isSys;
    }

    public void setSys(Boolean sys)
    {
        this.isSys = sys;
    }

    public List<String> getMenus()
    {
        return menus;
    }

    public void setMenus(List<String> menus)
    {
        this.menus = menus;
    }

    public String getOfficeId()
    {
        return officeId;
    }

    public void setOfficeId(String officeId)
    {
        this.officeId = officeId;
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
        return "RoleDTO{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", nameen='" + nameen + '\'' + ", scope='" + scope + '\'' + ", sort=" + sort + ", isSys=" + isSys + ", menus=" + menus + ", officeId='" + officeId + '\'' + ", activated=" + activated + '}';
    }
}
