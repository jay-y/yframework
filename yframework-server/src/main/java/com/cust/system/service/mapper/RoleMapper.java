package com.cust.system.service.mapper;

import com.cust.system.domain.Role;
import com.cust.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Author: zk
 */

@Mapper(componentModel = "spring")
public interface RoleMapper extends AuditingEntityMapper<Role, RoleDTO>
{
    @Mapping(target = "menus", ignore = true)
    RoleDTO doToDto(Role role);

    List<RoleDTO> dosToDtos(List<Role> dos);

    Role dtoToDo(RoleDTO dto);

    List<Role> dtosToDos(List<RoleDTO> dtos);

    default Role doFromId(String id)
    {
        if (id == null)
        {
            return null;
        }
        Role role = new Role();
        role.setId(id);
        return role;
    }

}
