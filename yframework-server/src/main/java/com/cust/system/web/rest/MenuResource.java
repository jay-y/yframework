package com.cust.system.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cust.system.service.MenuService;
import com.cust.system.service.dto.MenuDTO;
import com.cust.system.web.vo.AccountVO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/mgt/menus")
public class MenuResource extends AbstractAuditingEntityResource<MenuDTO>
{
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuService menuService;

    public MenuResource(MenuService menuService)
    {
        this.menuService = menuService;
    }

    @Override
    public MenuService getService()
    {
        return menuService;
    }

    /**
     * 查询出根目录的所有子孙菜单(树形结构)
     *
     * @param rootId
     * @return
     */
    @GetMapping("/query/{rootId}")
    @Timed
    public ResponseEntity<List<MenuDTO>> all4RootId(@PathVariable String rootId)
    {
        log.debug("REST request to searchAllMenusWithRootId,rootId: {}", rootId);
        return ResponseEntity.ok(getService().findAll4TreeWithMenuId(rootId));
    }

    @GetMapping("/account")
    @Timed
    public ResponseEntity<Set<MenuDTO>> all4Authorities(@ApiParam(required = true) AccountVO dto)
    {
        log.debug("REST request to get all menus with authorities, account: {}", dto);
        return ResponseEntity.ok(getService().findAll4Roles(dto.getAuthorities()));
    }

    /**
     * 查询除根目录之外的所有菜单
     *
     * @param dto
     * @return
     */
    @GetMapping("/normal")
    public ResponseEntity<List<MenuDTO>> all4Normal(@ApiParam(required = true) MenuDTO dto)
    {
        log.debug("REST request to get all for normal, entity: {}", dto);
        return ResponseEntity.ok(menuService.findAll4Normal(dto));
    }
}
