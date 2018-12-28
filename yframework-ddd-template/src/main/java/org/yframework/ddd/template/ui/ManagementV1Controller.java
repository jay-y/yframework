package org.yframework.ddd.template.ui;

import org.springframework.web.bind.annotation.*;
import org.yframework.ddd.template.core.application.service.ManagementApplicationService;
import org.yframework.ddd.template.core.domain.model.Management;
import org.yframework.ddd.common.ui.IController;
import org.yframework.toolkit.StringUtil;

import java.util.Map;

/**
 * Description: 管理资源接口.<br>
 * Date: 2018/10/7 下午3:55<br>
 * Author: ysj
 */
@RestController
@RequestMapping(value = "/mgt/v1")
public class ManagementV1Controller implements IController
{
    private final ManagementApplicationService commonManagementApplicationService;

    public ManagementV1Controller(ManagementApplicationService commonManagementApplicationService)
    {
        this.commonManagementApplicationService = commonManagementApplicationService;
    }

    @PostMapping(value = "/{resoureId}")
    public Map create(@PathVariable String resoureId, @RequestBody Management dto)
    {
        return this.commonManagementApplicationService.saveOne(resoureId, dto);
    }

    @PutMapping(value = "/{resoureId}/{id}")
    public Map update(@PathVariable String resoureId, @PathVariable String id, @RequestBody Management dto)
    {
        if (StringUtil.isBlank(id))
        {
//            throw new RuntimeException("资源不存在");
            return this.create(resoureId, dto);
        }
        return this.commonManagementApplicationService.saveOne(resoureId, dto);
    }

    @GetMapping(value = "/{resoureId}/{id}")
    public Map getOne(@PathVariable String resoureId, @PathVariable String id)
    {
        return this.commonManagementApplicationService.getOneById(resoureId, id);
    }

    @DeleteMapping(value = "/{resoureId}/{id}")
    public Boolean deleteOne(@PathVariable String resoureId, @PathVariable String id)
    {
        return this.commonManagementApplicationService.deleteOne(resoureId, id);
    }
}