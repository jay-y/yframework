package org.yframework.ddd.template.core.application.service;

import org.springframework.stereotype.Service;
import org.yframework.ddd.template.core.application.repository.ManagementRepository;
import org.yframework.ddd.template.core.domain.model.Management;
import org.yframework.ddd.template.si.persistence.mybatis.config.DatatableProperties;

/**
 * Description: 通用管理应用服务.<br>
 * Date: 2018/10/15 上午12:05<br>
 * Author: ysj
 */
@Service
public class ManagementApplicationService
{
    private final ManagementRepository commonManagementRepository;

    private final DatatableProperties datatableProperties;

    public ManagementApplicationService(ManagementRepository commonManagementRepository, DatatableProperties datatableProperties)
    {
        this.commonManagementRepository = commonManagementRepository;
        this.datatableProperties = datatableProperties;
    }

    public Management saveOne(String resoureId, Management t)
    {
        t.setResourceId(resoureId);
        return t.save();
    }

    public Management getOneById(String resoureId, String id)
    {
        Management t = this.newManagement();
        t.setResourceId(resoureId);
        t.setId(id);
        return t.retrieve();
    }

    public boolean deleteOne(String resoureId, String id)
    {
        Management t = this.newManagement();
        t.setResourceId(resoureId);
        t.setId(id);
        return t.delete();
    }

    private Management newManagement()
    {
        Management t = new Management(this.commonManagementRepository);
        t.setLocation(this.datatableProperties.getLocation());
        t.setCharset(this.datatableProperties.getCharset());
        return t;
    }
}
