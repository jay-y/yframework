package com.cust.system.service.mapper;

import com.cust.system.domain.User;
import com.cust.system.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Description: UserMapper.<br>
 * Date: 2017/9/6 21:26<br>
 * Author: ysj
 */

/**
 * Mapper for the entity User and its DTO UserDTO.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends AuditingEntityMapper<User, UserDTO>
{
    @Mapping(target = "oldPassWord", ignore = true)
    UserDTO doToDto(User user);

    List<UserDTO> dosToDtos(List<User> dos);

    User dtoToDo(UserDTO dto);

    List<User> dtosToDos(List<UserDTO> dtos);

    default User doFromId(String id)
    {
        if (id == null)
        {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

//    default Set<String> stringsFromAuthorities(Set<Authority> authorities)
//    {
//        return authorities.stream().map(Authority::getName).collect(Collectors.toSet());
//    }
//
//    default Set<Authority> authoritiesFromStrings(Set<String> strings)
//    {
//        return strings.stream().map(string ->
//        {
//            Authority auth = new Authority();
//            auth.setName(string);
//            return auth;
//        }).collect(Collectors.toSet());
//    }
}
