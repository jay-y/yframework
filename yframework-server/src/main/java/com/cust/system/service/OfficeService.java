package com.cust.system.service;

import com.cust.system.domain.Office;
import com.cust.system.repository.OfficeRepository;
import com.cust.system.service.dto.OfficeDTO;
import com.cust.system.service.mapper.OfficeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yframework.mybatis.auditing.service.AbstractAuditingEntityService;

/**
 * Author: zk
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends AbstractAuditingEntityService<Office, OfficeDTO>
{
    private final OfficeMapper officeMapper;
    private final OfficeRepository officeRepository;

    public OfficeService(OfficeMapper officeMapper, OfficeRepository officeRepository)
    {
        this.officeMapper = officeMapper;
        this.officeRepository = officeRepository;
    }

    @Override
    public OfficeRepository getRepository()
    {
        return officeRepository;
    }

    @Override
    public OfficeMapper getMapper()
    {
        return officeMapper;
    }
}
