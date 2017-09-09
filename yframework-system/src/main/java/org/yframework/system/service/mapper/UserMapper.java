package org.yframework.system.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;
import org.yframework.system.domain.User;
import org.yframework.system.service.dto.UserDTO;

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
    UserDTO doToDto(User user);

    List<UserDTO> dosToDtos(List<User> dos);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
//    @Mapping(target = "activationKey", ignore = true)
//    @Mapping(target = "resetKey", ignore = true)
//    @Mapping(target = "resetDate", ignore = true)
//    @Mapping(target = "password", ignore = true)
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
