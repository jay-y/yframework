package com.cust.system.service;

import com.becypress.toolkit.error.CustomParameterizedException;
import com.cust.common.config.Constants;
import com.cust.system.domain.Role;
import com.cust.system.domain.RoleMenuRelation;
import com.cust.system.repository.RoleMenuRelationRepository;
import com.cust.system.repository.RoleRepository;
import com.cust.system.service.dto.RoleDTO;
import com.cust.system.service.mapper.RoleMapper;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: zk
 */
@Service
@CacheConfig(cacheNames = "cache-datas")
@Transactional(readOnly = true)
public class RoleService extends AbstractAuditingEntityService<Role, RoleDTO>
{
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final RoleMenuRelationRepository roleMenuRelationRepository;

    public RoleService(RoleMapper roleMapper, RoleRepository roleRepository, RoleMenuRelationRepository roleMenuRelationRepository)
    {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.roleMenuRelationRepository = roleMenuRelationRepository;
    }

    @Override
    public RoleRepository getRepository()
    {
        return roleRepository;
    }

    @Override
    public RoleMapper getMapper()
    {
        return roleMapper;
    }


    @Cacheable(key = _CACHE_KEY_SP_ONE)
    public RoleDTO findOne(RoleDTO dto)
    {
        Role e = this.getMapper().dtoToDo(dto);
        dto = this.getMapper().doToDto(this.getRepository().findOne(e));
        this.setMenus4RoleDTO(dto);
        return dto;
    }

    @Cacheable(key = _CACHE_KEY_SP_ONE)
    public RoleDTO findOneById(RoleDTO dto)
    {
        Role e = this.getMapper().dtoToDo(dto);
        dto = this.getMapper().doToDto(this.getRepository().findOneById(e));
        this.setMenus4RoleDTO(dto);
        return dto;
    }

    @Caching(put = {
            @CachePut(key = _CACHE_KEY_SP_ONE)
    }, evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL), @CacheEvict(key = _CACHE_KEY_SP_ONE)
    })
    @Transactional
    public RoleDTO create(RoleDTO dto)
    {
        checkRole4Create(dto);

        dto = this.save(dto);
        updateRoleMenuRelation(dto);
        return dto;
    }

    @Caching(put = {
            @CachePut(key = _CACHE_KEY_SP_ONE)
    }, evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL), @CacheEvict(key = _CACHE_KEY_SP_ONE)
    })
    @Transactional
    public RoleDTO update(RoleDTO dto)
    {
        checkRole4Update(dto);

        updateRoleMenuRelation(dto);
        dto = this.save(dto);
        return dto;
    }

    @Caching(evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL), @CacheEvict(key = _CACHE_KEY_SP_ONE)
    })
    @Transactional
    public void freezeAndDeleteRelation(RoleDTO dto)
    {
        this.freeze(dto);
        roleMenuRelationRepository.deleteByRoleId(dto.getId());
    }

    @Transactional
    public void updateRoleMenuRelation(RoleDTO roleDTO)
    {
        String roleId = roleDTO.getId();
        roleMenuRelationRepository.deleteByRoleId(roleId);

        List<String> menuIds = roleDTO.getMenus();
        for (String menuId : menuIds)
        {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation(roleId, menuId);
            roleMenuRelationRepository.save(roleMenuRelation);
        }
    }

    public void setMenus4RoleDTOList(List<RoleDTO> roleDTOList)
    {
        for (RoleDTO roleDTO : roleDTOList)
        {
            setMenus4RoleDTO(roleDTO);
        }
    }

    public void setMenus4RoleDTO(RoleDTO roleDTO)
    {
        List<String> menusIdList = roleMenuRelationRepository.findMenusIdByRoleId(roleDTO.getId());
        roleDTO.setMenus(menusIdList);
    }

    public List<RoleDTO> findAll4Normal()
    {
        List<Role> list = roleRepository.findList(new Role()).
                parallelStream().
                filter(o -> !o.getId().equals(Constants._ROOT_ID)).
                collect(Collectors.toList());
        return roleMapper.dosToDtos(list);
    }

    public List<RoleDTO> findRolesByUserId(String uid)
    {
        List<Role> list = roleRepository.findRolesByUserId(uid);
        return roleMapper.dosToDtos(list);
    }

    private void checkRole4Create(RoleDTO roleDTO)
    {
        Role roleCheck = roleRepository.findAllRolesByName(roleDTO.getName());
        if (null != roleCheck)
        {
            throw new CustomParameterizedException("该中文名称已经存在");
        }
        roleCheck = roleRepository.findAllRolesByNameen(roleDTO.getNameen());
        if (null != roleCheck)
        {
            throw new CustomParameterizedException("该英文名称已经存在");
        }
    }

    private void checkRole4Update(RoleDTO roleDTO)
    {
        Role roleCheck = roleRepository.findAllRolesByName(roleDTO.getName());
        if (null != roleCheck && !roleDTO.getId().equals(roleCheck.getId()))
        {
            throw new CustomParameterizedException("该中文名称已经存在");
        }
        roleCheck = roleRepository.findAllRolesByNameen(roleDTO.getNameen());
        if (null != roleCheck && !roleDTO.getId().equals(roleCheck.getId()))
        {
            throw new CustomParameterizedException("该英文名称已经存在");
        }
    }
}
