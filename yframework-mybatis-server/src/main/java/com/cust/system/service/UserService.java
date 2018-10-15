package com.cust.system.service;

import com.becypress.toolkit.error.CustomParameterizedException;
import com.cust.security.utils.SecurityUtil;
import com.cust.system.domain.User;
import com.cust.system.domain.UserRoleRelation;
import com.cust.system.repository.UserRepository;
import com.cust.system.repository.UserRoleRelationRepository;
import com.cust.system.service.dto.UserDTO;
import com.cust.system.service.mapper.UserMapper;
import org.springframework.cache.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;
import org.yframework.toolkit.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: UserService.<br>
 * Date: 2017/9/6 21:26<br>
 * Author: ysj
 */
@Service
@CacheConfig(cacheNames = "cache-datas")
@Transactional(readOnly = true)
public class UserService extends AbstractAuditingEntityService<User, UserDTO>
{
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRelationRepository userRoleRelationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, UserRepository userRepository, UserRoleRelationRepository userRoleRelationRepository, PasswordEncoder passwordEncoder)
    {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userRoleRelationRepository = userRoleRelationRepository;
        this.passwordEncoder = passwordEncoder;
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


    @Caching(put = {
            @CachePut(key = _CACHE_KEY_SP_ONE)
    }, evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL), @CacheEvict(key = _CACHE_KEY_SP_ONE)
    })
    @Transactional
    public UserDTO create(UserDTO dto)
    {
        checkUser4Create(dto);

        dto.setLogin(dto.getLogin().toLowerCase());
        if (StringUtil.isBlank(dto.getLangKey()))
        {
            dto.setLangKey("zh-cn"); // default language
        }
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encryptedPassword);
        dto = this.save(dto);
        updateUserRoleRelation(dto);

        dto.setPassword(null);
        dto.setOldPassWord(null);
        return dto;
    }

    @Caching(put = {
            @CachePut(key = _CACHE_KEY_SP_ONE)
    }, evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL), @CacheEvict(key = _CACHE_KEY_SP_ONE)
    })
    @Transactional
    public UserDTO update(UserDTO dto)
    {
        checkUser4Update(dto);

        updateUserRoleRelation(dto);
        dto.setLogin(dto.getLogin().toLowerCase());
        dto = this.save(dto);
        return dto;
    }

    @Transactional
    public void updateUserRoleRelation(UserDTO userDTO)
    {
        String userId = userDTO.getId();
        userRoleRelationRepository.deleteByUserId(userId);

        List<String> roleIds = userDTO.getAuthorities();
        for (String roleId : roleIds)
        {
            UserRoleRelation userRoleRelation = new UserRoleRelation(userId, roleId);
            userRoleRelationRepository.save(userRoleRelation);
        }
    }

    @CacheEvict(allEntries = true)
    @Transactional
    public void deleteWithLogin(String login)
    {
        String currentLogin = SecurityUtil.INSTANCE.getCurrentUserLogin();
        if (login.equals(currentLogin))
        {
            throw new CustomParameterizedException("不能删除用户本身");
        }

        User user = userRepository.findUserByLogin(login);
        if (null != user)
        {
            userRoleRelationRepository.deleteByUserId(user.getId());
            this.freeze(userMapper.doToDto(user));
        }
    }

    @Caching(put = {
            @CachePut(key = _CACHE_KEY_SP_ONE)
    }, evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL), @CacheEvict(key = _CACHE_KEY_SP_ONE)
    })
    @Transactional
    public UserDTO resetPassWord(UserDTO dto)
    {
        String oldPwd = dto.getOldPassWord();
        String newPwd = dto.getPassword();
        if (StringUtil.isBlank(oldPwd) || StringUtil.isBlank(newPwd))
        {
            throw new CustomParameterizedException("修改交易密码失败，新旧密码不能为空");
        }

        if (StringUtil.isBlank(dto.getId()))
        {
            throw new CustomParameterizedException("用户ID为空");
        }

        User userLocal = userRepository.findUserById(dto.getId());
        if (null == userLocal)
        {
            throw new CustomParameterizedException("用户不存在");
        }

        if (!passwordEncoder.matches(oldPwd, userLocal.getPassword()))
        {
            throw new CustomParameterizedException("原密码不匹配，请重新输入");
        }

        String newPwdEncry = passwordEncoder.encode(newPwd);
        userLocal.setPassword(newPwdEncry);
        userRepository.update(userLocal);

        UserDTO dtoLocal = userMapper.doToDto(userLocal);
        dtoLocal.setPassword(null);
        dtoLocal.setOldPassWord(null);
        return dtoLocal;
    }

    @Override
    @Cacheable(key = "#root.targetClass.name + \'-\' + #dto.id")
    public UserDTO findOne(UserDTO dto)
    {
        dto = super.findOne(dto);
        if (null != dto)
        {
            List<String> rolesIdList = userRoleRelationRepository.findRolesIdByUserId(dto.getId());
            dto.setAuthorities(rolesIdList);
        }
        return dto;
    }

    /**
     * 查询所有用户（包含角色信息）
     *
     * @return
     */
    public List<UserDTO> getAllWithRoles()
    {
        List<UserDTO> userDTOList = super.findAll();
        setRoles4UserDTOList(userDTOList);
        return userDTOList;
    }

    public User getUserWithAuthorities()
    {
        return getUserWithAuthorities(SecurityUtil.INSTANCE.getCurrentUserLogin());
    }

    public User getUserWithAuthorities(String login)
    {
        User user = userRepository.findUserByLogin(login);
        setRoles4User(user);
        return user;
    }

    public void setRoles4UserDTOList(List<UserDTO> userDTOList)
    {
        Map<String, List<String>> map = getAllUserRoleRelation2Map();
        for (UserDTO userDTO : userDTOList)
        {
            if (map.containsKey(userDTO.getId()))
            {
                List<String> authorities = map.get(userDTO.getId());
                userDTO.setAuthorities(authorities);
            }
        }
    }

    /**
     * 查询出所有的UserRoleRelation，并组装成Map<UserId, List<RoleId>>
     *
     * @return
     */
    public Map<String, List<String>> getAllUserRoleRelation2Map()
    {
        List<UserRoleRelation> userRoleRelationList = userRoleRelationRepository.findAllUserRoleRelation();
        Map<String, List<String>> resultMap = new HashMap<>();
        for (UserRoleRelation userRoleRelation : userRoleRelationList)
        {
            String userId = userRoleRelation.getUserId();
            String roleId = userRoleRelation.getRoleId();
            List<String> authorities = null;
            if (resultMap.containsKey(userId))
            {
                authorities = resultMap.get(userId);
            }
            else
            {
                authorities = new ArrayList<>();
            }
            authorities.add(roleId);
            resultMap.put(userId, authorities);
        }
        return resultMap;
    }

    public void setRoles4User(User user)
    {
        if (user != null)
        {
            List<String> rolesIdList = userRoleRelationRepository.findRolesIdByUserId(user.getId());
            user.setAuthorities(rolesIdList);
        }
    }

    private void checkUser4Create(UserDTO userDTO)
    {
        User userCheck = userRepository.findUserByLogin(userDTO.getLogin().toLowerCase());
        if (null != userCheck)
        {
            throw new CustomParameterizedException("该登陆名已经存在");
        }

        if (StringUtil.isNotBlank(userDTO.getEmail()))
        {
            userCheck = userRepository.findUserByEmail(userDTO.getEmail());
            if (null != userCheck)
            {
                throw new CustomParameterizedException("该邮箱已经被使用了");
            }
        }
    }

    private void checkUser4Update(UserDTO userDTO)
    {
        User userCheck = userRepository.findUserByLogin(userDTO.getLogin().toLowerCase());
        if (null != userCheck && !userDTO.getId().equals(userCheck.getId()))
        {
            throw new CustomParameterizedException("该登陆名已经存在");
        }

        if (StringUtil.isNotBlank(userDTO.getEmail()))
        {
            userCheck = userRepository.findUserByEmail(userDTO.getEmail());
            if (null != userCheck && !userDTO.getId().equals(userCheck.getId()))
            {
                throw new CustomParameterizedException("该邮箱已经被使用了");
            }
        }
    }
}
