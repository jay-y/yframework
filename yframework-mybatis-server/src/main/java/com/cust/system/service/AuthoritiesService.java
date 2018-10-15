package com.cust.system.service;

import com.becypress.toolkit.Becypress;
import com.cust.common.utils.CacheUtil;
import com.cust.system.domain.Menu;
import com.cust.system.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.toolkit.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: AuthoritiesService.<br>
 * Date: 2017/8/8 18:52<br>
 * Author: ysj
 */
@Transactional(readOnly = true)
@Service
public class AuthoritiesService
{
    public final static String _CACHE_KEY_AUTHORITIES = "CACHE_KEY_AUTHORITIES";
    private final MenuRepository menuRepository;
    private final UserService userService;
    private final CacheUtil cache;

    private final String ROOT_ID = "1";

    public AuthoritiesService(MenuRepository menuRepository, UserService userService, CacheUtil cache)
    {
        this.menuRepository = menuRepository;
        this.userService = userService;
        this.cache = cache;
    }

    public Set<Menu> findMenusByRoles(Set<String> authorities)
    {
        //合并出多角色授权菜单集合
        Set<Menu> menus = new HashSet<>();
        String key;
        List<Menu> menuList;
        if (authorities.contains(ROOT_ID))
        {
            key = _CACHE_KEY_AUTHORITIES;

        }
        else
        {
            key = _CACHE_KEY_AUTHORITIES + Becypress.UTIL.extra().json().toJson(authorities);
        }
        Menu root = null;
//        Menu root = (Menu) cache.get(BecypressApplication.class).get(key);
        if (null == root)
        {
            //根据角色查询菜单集合
            if (_CACHE_KEY_AUTHORITIES.equals(key))
            {
                menuList = menuRepository.findAll(Menu.class);
            }
            else
            {
                authorities.parallelStream().forEach(authority ->
                {
                    List<Menu> menusByRoleId = menuRepository.findMenusByRoleId(authority);
                    menus.addAll(menusByRoleId);
                });
                menuList = new ArrayList<>(menus);
            }
            //整合父子菜单关系
            root = this.genMenu(menuList, ROOT_ID);
            for (int i = 0; i < menuList.size(); )
            {
                Menu temp = menuList.get(i);
                String parentId = temp.getParentId();
                if (StringUtil.isNoneBlank(parentId) && ROOT_ID.equals(parentId))
                {
                    Menu menu = this.genMenu(menuList, temp.getId());
                    root.getChildren().add(menu);
                    if (menuList.size() > 0)
                    {
                        menuList.remove(temp);
                    }
                    i = 0;
                }
                else
                {
                    i++;
                }
            }
//            cache.get(ServerApp.class).put(key, root);
            menus.clear();
        }
        menus.add(root);
        return menus;
    }

//    public void clearMenus()
//    {
//        User curr = userService.getUserWithAuthorities(SecurityUtil.INSTANCE.getCurrentUserLogin());
//        if (null != curr.getAuthorities())
//        {
//            String key;
//            if (curr.getAuthorities().contains(ROOT_ID))
//            {
//                key = _CACHE_KEY_AUTHORITIES;
//
//            }
//            else
//            {
//                key = _CACHE_KEY_AUTHORITIES + Becypress.UTIL.extra().util().json().toJson(curr.getAuthorities());
//
//            }
//            cache.get(ServerApp.class).remove(key);
//        }
//    }

    private Menu genMenu(List<Menu> menuList, String parentId)
    {
        Menu root = null;
        for (int i = 0; i < menuList.size(); i++)
        {
            Menu menu = menuList.get(i);
            if (parentId.equals(menu.getId()))
            {
                root = menu;
                root.getChildren().clear();
                menuList.remove(i);
                i--;
                if (ROOT_ID.equals(parentId))
                {
                    break;
                }
            }
            else
            {
                if (null == root)
                {
                    menuList.remove(menu);
                    menuList.add(menu);
                    i--;
                }
                else if (null != root && !ROOT_ID.equals(parentId) && StringUtil.isNoneBlank(menu.getParentId()) && parentId.equals(menu.getParentId()))
                {
                    root.getChildren().add(menu);
                    menuList.remove(menu);
                    i--;
                }
            }
        }
        return root;
    }
}
