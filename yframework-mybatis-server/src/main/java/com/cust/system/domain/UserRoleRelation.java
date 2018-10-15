package com.cust.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sys_user_role")
public class UserRoleRelation
{
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;

    public UserRoleRelation()
    {
    }

    public UserRoleRelation(String userId, String roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
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

        UserRoleRelation obj = (UserRoleRelation) o;

        return userId.equals(obj.getUserId()) && roleId.equals(obj.getRoleId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getUserId() + getRoleId());
    }

    @Override
    public String toString()
    {
        return "UserRoleRelation{" + "userId='" + userId + '\'' + ", roleId='" + roleId + '\'' + '}';
    }
}
