package com.cust.system.service;

import com.cust.system.domain.Dict;
import com.cust.system.repository.DictRepository;
import com.cust.system.service.dto.DictDTO;
import com.cust.system.service.mapper.DictMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;

import java.util.List;

/**
 * Author: zk
 */
@Service
@Transactional(readOnly = true)
public class DictService extends AbstractAuditingEntityService<Dict, DictDTO>
{
    private final DictMapper dictMapper;
    private final DictRepository dictRepository;

    public DictService(DictMapper dictMapper, DictRepository dictRepository)
    {
        this.dictMapper = dictMapper;
        this.dictRepository = dictRepository;
    }

    @Override
    public DictRepository getRepository()
    {
        return dictRepository;
    }

    @Override
    public DictMapper getMapper()
    {
        return dictMapper;
    }

    public List<DictDTO> findAllByType(String type)
    {
        List<Dict> list = dictRepository.findAllByType(type);
        return dictMapper.dosToDtos(list);
    }
}
