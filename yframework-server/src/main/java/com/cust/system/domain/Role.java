package com.cust.system.domain;

import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author zk
 */
@Entity
@Table(name = "sys_role")
public class Role extends AbstractAuditingEntity<String>
{
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "nameen")
    private String nameen;

    @Column(name = "scope")
    private String scope;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "is_sys")
    private Boolean isSys;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "sys_role_menu", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
//    @OrderBy(value = "sort ASC")
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    private List<Menu> menus = new ArrayList<>();

    //    @ManyToOne
    @Column(name = "office_id")
    private String officeId;


    public Role()
    {
    }

    public Role(String id)
    {
        super(id);
    }

    public String getName()
    {
        return name;
    }

    public Role name(String name)
    {
        this.name = name;
        return this;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNameen()
    {
        return nameen;
    }

    public Role nameen(String nameen)
    {
        this.nameen = nameen;
        return this;
    }

    public void setNameen(String nameen)
    {
        this.nameen = nameen;
    }

    public String getScope()
    {
        return scope;
    }

    public Role scope(String scope)
    {
        this.scope = scope;
        return this;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public Integer getSort()
    {
        return sort;
    }

    public Role sort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Boolean isSys()
    {
        return isSys;
    }

    public Role isSys(Boolean isSys)
    {
        this.isSys = isSys;
        return this;
    }

    public void setSys(Boolean sys)
    {
        this.isSys = sys;
    }

    public String getOfficeId()
    {
        return officeId;
    }

    public void setOfficeId(String officeId)
    {
        this.officeId = officeId;
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
        Role role = (Role) o;
        if (role.getId() == null || getId() == null)
        {
            return false;
        }
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString()
    {
        return "Role{" + "name='" + name + '\'' + ", nameen='" + nameen + '\'' + ", scope='" + scope + '\'' + ", sort=" + sort + ", isSys=" + isSys + ", officeId='" + officeId + '\'' + '}' + super.toString();
    }
}
