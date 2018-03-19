package com.cust.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sys_role_menu")
public class RoleMenuRelation
{
    private static final long serialVersionUID = 1L;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "menu_id")
    private String menuId;

    public RoleMenuRelation()
    {
    }

    public RoleMenuRelation(String roleId, String menuId)
    {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
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

        RoleMenuRelation obj = (RoleMenuRelation) o;

        return menuId.equals(obj.getMenuId()) && roleId.equals(obj.getRoleId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getMenuId() + getRoleId());
    }

    @Override
    public String toString()
    {
        return "RoleMenuRelation{" + "roleId='" + roleId + '\'' + ", menuId='" + menuId + '\'' + '}';
    }
}
