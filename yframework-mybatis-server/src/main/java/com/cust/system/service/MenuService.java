package com.cust.system.service;

import com.becypress.toolkit.Becypress;
import com.cust.common.config.Constants;
import com.cust.common.utils.CacheUtil;
import com.cust.security.config.AuthoritiesConstants;
import com.cust.system.domain.Menu;
import com.cust.system.repository.MenuRepository;
import com.cust.system.service.dto.MenuDTO;
import com.cust.system.service.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: zk
 */
@Service
@Transactional(readOnly = true)
public class MenuService extends AbstractAuditingEntityService<Menu, MenuDTO>
{
    public final static String _CACHE_KEY_AUTHORITIES = "CACHE_KEY_AUTHORITIES";
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final CacheUtil cache;

    public MenuService(MenuMapper menuMapper, MenuRepository menuRepository, CacheUtil cache)
    {
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
        this.cache = cache;
    }

    @Override
    public MenuRepository getRepository()
    {
        return menuRepository;
    }

    @Override
    public MenuMapper getMapper()
    {
        return menuMapper;
    }

    public Set<MenuDTO> findAll4Roles(List<String> authorities)
    {
        //合并出多角色授权菜单集合
        Set<Menu> menus = new HashSet<>();
        String key;
        List<Menu> menuList;
        if (authorities.contains(AuthoritiesConstants.ADMIN))
        {
            key = _CACHE_KEY_AUTHORITIES;
        }
        else
        {
            key = _CACHE_KEY_AUTHORITIES + Becypress.UTIL.extra().json().toJson(authorities);
        }

        MenuDTO root = null;
//        MenuDTO root = (MenuDTO) cache.get(MenuService.class).get(key).get();
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
                    List<Menu> menusByRoleId = getRepository().findMenusByRoleId(authority);
                    menus.addAll(menusByRoleId);
                });
                menuList = new ArrayList<>(menus);
            }

            Map<String, MenuDTO> allMenuDTOsMap = this.constructingTree4MenuDTOList(this.getMapper().dosToDtos(menuList));
            root = allMenuDTOsMap.get(AuthoritiesConstants.ADMIN);
//            cache.get(MenuService.class).put(key, root);
        }

        Set<MenuDTO> result = new HashSet<>();
        result.add(root);
        return result;
    }

    public List<MenuDTO> findAll4Normal(MenuDTO dto)
    {
        return this.findAll(dto).
                parallelStream().
                filter(o -> !o.getId().equals(Constants._ROOT_ID)).
                collect(Collectors.toList());
    }

    /**
     * 返回某个菜单下的子孙菜单(树形结构)
     *
     * @param menuId
     * @return
     */
    public List<MenuDTO> findAll4TreeWithMenuId(String menuId)
    {
        Map<String, MenuDTO> allMenuDTOsMap = findAll4Tree();
        MenuDTO root = allMenuDTOsMap.get(menuId);
        return root.getChildren();
    }

    /**
     * 查询出所有菜单，构建成树形结构
     *
     * @return
     */
    public Map<String, MenuDTO> findAll4Tree()
    {
        List<Menu> allMenuList = menuRepository.findAllMenus();
        List<MenuDTO> allMenuDTOList = menuMapper.dosToDtos(allMenuList);
        return constructingTree4MenuDTOList(allMenuDTOList);
    }

    /**
     * 将菜单集合构建成树形结构
     *
     * @param menuDTOList
     * @return
     */
    public Map<String, MenuDTO> constructingTree4MenuDTOList(List<MenuDTO> menuDTOList)
    {
        Map<String, MenuDTO> menuDTOsMap = new HashMap<>();
        for (MenuDTO aMenuDTOList : menuDTOList)
        {
            menuDTOsMap.put(aMenuDTOList.getId(), aMenuDTOList);
        }
        Set<String> menuDTOIdSet = menuDTOsMap.keySet();
        for (String menuDTOId : menuDTOIdSet)
        {
            MenuDTO curMenuDTO = menuDTOsMap.get(menuDTOId);
            String parentId = curMenuDTO.getParentId();
            if (menuDTOsMap.containsKey(parentId))
            {
                menuDTOsMap.get(parentId).addChild(curMenuDTO);
            }
        }
        return menuDTOsMap;
    }


}
