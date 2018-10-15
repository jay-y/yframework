package org.yframework.ddd.template.core.domain.model;

import org.springframework.data.annotation.Persistent;
import org.yframework.ddd.template.core.application.repository.ManagementRepository;
import org.yframework.ddd.template.si.persistence.mybatis.model.CommonAggregateRoot;
import org.yframework.toolkit.StringUtil;

import java.util.UUID;

/**
 * Description: 管理.<br>
 * Date: 2018/10/15 上午1:46<br>
 * Author: ysj
 */
public class Management extends CommonAggregateRoot
{
    @Persistent
    private ManagementRepository managementRepository;

    public Management()
    {
    }

    public Management(ManagementRepository managementRepository)
    {
        this.managementRepository = managementRepository;
    }

    public ManagementRepository getManagementRepository()
    {
        return managementRepository;
    }

    public Management save()
    {
        if (StringUtil.isNotBlank(this.getId()))
        {
            this.getManagementRepository().update(this);
        }
        else
        {
            this.setId(this.generateId());
            this.getManagementRepository().create(this);
        }
        return this;
    }

    public Management retrieve()
    {
        return this.getManagementRepository().retrieve(this);
    }

    public boolean delete()
    {
        return this.getManagementRepository().delete(this);
    }

    protected String generateId()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
