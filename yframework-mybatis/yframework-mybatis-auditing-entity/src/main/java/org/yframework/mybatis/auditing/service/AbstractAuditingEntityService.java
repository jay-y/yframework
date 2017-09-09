package org.yframework.mybatis.auditing.service;

import com.github.pagehelper.PageHelper;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.domain.AuditingEntity;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;
import org.yframework.toolkit.StringUtil;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Description: AuditingEntityServiceImpl.<br>
 * Date: 2017/9/8 14:51<br>
 * Author: ysj
 */
@CacheConfig(cacheNames = "cache-datas")
@Transactional(readOnly = true)
public abstract class AbstractAuditingEntityService<DO extends AuditingEntity, DTO extends AuditingEntityDTO> implements AuditingEntityService<DO, DTO>
{
    private static final String _CACHE_KEY_SP_ONE = "#root.targetClass.name + '-' + #dto.id";
    private static final String _CACHE_KEY_SP_ALL = "#root.targetClass.name + '-all'";
    private static final String _CACHE_KEY_SP_COUNT_ALL = "#root.targetClass.name + '-count'";

    private final Class<DO> cls;

    protected AbstractAuditingEntityService()
    {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.cls = (Class<DO>) pt.getActualTypeArguments()[0];
    }

    @Override
    @Caching(put = {
            @CachePut(key = _CACHE_KEY_SP_ONE)
    }, evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ONE), @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL)
    })
    @Transactional
    public DTO save(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        if (null != dto.getId() && StringUtil.isNotEmpty(dto.getId().toString()))
        {
            getRepository().update(e);
        }
        else
        {
            getRepository().insert(e);
            dto.setId(e.getId());
        }
        return dto;
    }

    @Override
    @Cacheable(key = _CACHE_KEY_SP_ONE)
    public DTO findOne(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        dto = getMapper().doToDto(getRepository().findOne(e));
        return dto;
    }

    @Override
    @Cacheable(key = _CACHE_KEY_SP_ALL)
    public List<DTO> findAll()
    {
        List<DO> es = getRepository().findAll(cls);
        return getMapper().dosToDtos(es);
    }

    @Override
    public List<DTO> findAll(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        List<DO> es = getRepository().findList(e);
        return getMapper().dosToDtos(es);
    }

    @Override
    public Page<DTO> findAll(DTO dto, Pageable pageable)
    {
//        DO e = getMapper().dtoToDo(dto);
//        List<DO> es = getRepository().findPage(e, pageable);
//        long count = getRepository().countWithEntity(e);
//        return new PageImpl<>(getMapper().dosToDtos(es), pageable, count);
        DO e = getMapper().dtoToDo(dto);
        com.github.pagehelper.Page<DO> pageTmp = PageHelper.
                startPage(pageable.getPageNumber(), pageable.getPageSize()).
                doSelectPage(() -> getRepository().findPage(e, pageable));
        Page<DO> page = new PageImpl<>(pageTmp.getResult(), pageable, pageTmp.getTotal());
        return page.map(aDo -> getMapper().doToDto(aDo));
    }

    @Override
    @Cacheable(key = _CACHE_KEY_SP_COUNT_ALL)
    public long count()
    {
        return getRepository().count(cls);
    }

    @Override
    public long count(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        return getRepository().countWithEntity(e);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ONE), @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL)
    })
    @Transactional
    public void activate(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        getRepository().activate(e);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ONE), @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL)
    })
    @Transactional
    public void freeze(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        getRepository().freeze(e);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = _CACHE_KEY_SP_ONE), @CacheEvict(key = _CACHE_KEY_SP_ALL), @CacheEvict(key = _CACHE_KEY_SP_COUNT_ALL)
    })
    @Transactional
    public void delete(DTO dto)
    {
        DO e = getMapper().dtoToDo(dto);
        getRepository().delete(e);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    public void deleteAll()
    {
        getRepository().deleteAll(cls);
    }
}
