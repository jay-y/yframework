package org.yframework.system.service;

import org.springframework.stereotype.Service;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;
import org.yframework.system.domain.User;
import org.yframework.system.repository.UserRepository;
import org.yframework.system.service.dto.UserDTO;
import org.yframework.system.service.mapper.UserMapper;

/**
 * Description: UserService.<br>
 * Date: 2017/9/6 21:26<br>
 * Author: ysj
 */
@Service
public class UserService extends AbstractAuditingEntityService<User, UserDTO>
{
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository)
    {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getRepository()
    {
        return userRepository;
    }

    @Override
    public UserMapper getMapper()
    {
        return userMapper;
    }

    public UserDTO findOneByLogin(String login)
    {
        User e = new User();
        e.setLogin(login);
        return getMapper().doToDto(getRepository().findOneByLogin(e));
    }

}
