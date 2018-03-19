package com.cust.system.service.mapper;

import com.cust.system.domain.Menu;
import com.cust.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Author: zk
 */

@Mapper(componentModel = "spring")
public interface MenuMapper extends AuditingEntityMapper<Menu, MenuDTO>
{
    MenuDTO doToDto(Menu menu);

    List<MenuDTO> dosToDtos(List<Menu> dos);

    Menu dtoToDo(MenuDTO dto);

    List<Menu> dtosToDos(List<MenuDTO> dtos);

    default Menu doFromId(String id)
    {
        if (id == null)
        {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(id);
        return menu;
    }

}
