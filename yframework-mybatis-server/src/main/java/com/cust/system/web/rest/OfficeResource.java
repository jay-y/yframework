package com.cust.system.web.rest;

import com.cust.system.service.OfficeService;
import com.cust.system.service.dto.OfficeDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;


@RestController
@RequestMapping("/mgt/offices")
public class OfficeResource extends AbstractAuditingEntityResource<OfficeDTO>
{
    private final OfficeService officeService;

    public OfficeResource(OfficeService officeService)
    {
        this.officeService = officeService;
    }

    @Override
    public OfficeService getService()
    {
        return officeService;
    }
}
