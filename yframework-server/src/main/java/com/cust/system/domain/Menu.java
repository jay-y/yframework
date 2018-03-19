package com.cust.system.domain;

import org.yframework.mybatis.auditing.domain.AbstractAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zk
 */
@Entity
@Table(name = "sys_menu")
public class Menu extends AbstractAuditingEntity<String>
{
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "href")
    private String href;

    @Column(name = "icon")
    private String icon;

    @Column(name = "permission")
    private String permission;

    @Column(name = "is_show")
    private Boolean isShow;

    //    @JsonIgnore
//    @ManyToOne
//    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "parent_id")
    private String parentId;

    //    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
//    @OrderBy(value = "sort ASC")
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<Menu> children = new ArrayList<>();

    public Menu()
    {
    }

    public Menu(String id)
    {
        super(id);
    }


    public String getName()
    {
        return name;
    }

    public Menu name(String name)
    {
        this.name = name;
        return this;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getSort()
    {
        return sort;
    }

    public Menu sort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getHref()
    {
        return href;
    }

    public Menu href(String href)
    {
        this.href = href;
        return this;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getIcon()
    {
        return icon;
    }

    public Menu icon(String icon)
    {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getPermission()
    {
        return permission;
    }

    public Menu permission(String permission)
    {
        this.permission = permission;
        return this;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public Boolean isShow()
    {
        return isShow;
    }

    public Menu isShow(Boolean isShow)
    {
        this.isShow = isShow;
        return this;
    }

    public void setShow(Boolean isShow)
    {
        this.isShow = isShow;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public List<Menu> getChildren()
    {
        return children;
    }

    public void setChildren(List<Menu> children)
    {
        this.children = children;
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
        Menu menu = (Menu) o;
        if (menu.getId() == null || getId() == null)
        {
            return false;
        }
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString()
    {
        return "Menu{" + "name='" + name + '\'' + ", sort=" + sort + ", href='" + href + '\'' + ", icon='" + icon + '\'' + ", permission='" + permission + '\'' + ", isShow=" + isShow + ", parentId='" + parentId + '\'' + ", children=" + children + '}' + super.toString();
    }
}
