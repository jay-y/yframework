package com.cust.system.service.dto;

import com.cust.system.domain.Menu;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zk
 */
public class MenuDTO implements AuditingEntityDTO<String>
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Integer sort;

    private String href;

    private String icon;

    private String permission;

    private Boolean isShow;

    private String parentId;

    private boolean activated = true;

    private List<MenuDTO> children = new ArrayList<>();


    public MenuDTO()
    {
    }

    public MenuDTO(String id)
    {
        this.id = id;
    }

    public MenuDTO(String id, String name, Integer sort, String href, String icon, String permission, Boolean isShow, String parentId, boolean activated)
    {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.href = href;
        this.icon = icon;
        this.permission = permission;
        this.isShow = isShow;
        this.parentId = parentId;
        this.activated = activated;
    }

    public MenuDTO(Menu menu)
    {
        this(menu.getId(), menu.getName(), menu.getSort(), menu.getHref(), menu.getIcon(), menu.getPermission(), menu.isShow(), menu.getParentId(), menu.isActivated());
    }

    /**
     * 添加一个子菜单（按sort排序）
     *
     * @param child
     * @return
     */
    public void addChild(MenuDTO child)
    {
        boolean addFlag = false; // 是否添加标识
        for (int i = 0; i < this.children.size(); i++)
        {
            MenuDTO menuDTO = this.children.get(i);
            if (menuDTO.getSort().compareTo(child.getSort()) > 0)
            {
                this.children.add(i, child);
                return;
            }
        }
        if (!addFlag)
        {
            this.children.add(child);
        }
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

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getPermission()
    {
        return permission;
    }

    public void setPermission(String permission)
    {
        this.permission = permission;
    }

    public Boolean isShow()
    {
        return isShow;
    }

    public void setShow(Boolean show)
    {
        isShow = show;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public List<MenuDTO> getChildren()
    {
        return children;
    }

    public void setChildren(List<MenuDTO> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "MenuDTO{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", sort=" + sort + ", href='" + href + '\'' + ", icon='" + icon + '\'' + ", permission='" + permission + '\'' + ", isShow=" + isShow + ", parentId='" + parentId + '\'' + ", activated=" + activated + ", children=" + children + '}';
    }
}
