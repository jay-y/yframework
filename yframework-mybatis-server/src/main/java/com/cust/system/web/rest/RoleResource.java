package com.cust.system.web.rest;

import com.cust.system.service.RoleService;
import com.cust.system.service.dto.RoleDTO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yframework.mybatis.auditing.utils.HeaderUtil;
import org.yframework.mybatis.auditing.utils.PaginationUtil;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;
import org.yframework.toolkit.y;

import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping("/mgt/roles")
public class RoleResource extends AbstractAuditingEntityResource<RoleDTO>
{
    private static final Logger log = LoggerFactory.getLogger(RoleResource.class);

    private static final String entityName = "Role";

    private final RoleService roleService;

    public RoleResource(RoleService roleService)
    {
        this.roleService = roleService;
    }

    @Override
    public RoleService getService()
    {
        return roleService;
    }

    @Override
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO) throws Exception
    {
        log.debug("REST request to save {} : {}", entityName, roleDTO);
        if (roleDTO.getId() != null)
        {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(entityName, "idexists", "A new vo cannot already have an ID")).body(null);
        }
        roleDTO = roleService.create(roleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(entityName, roleDTO.getId().toString())).body(roleDTO);
    }

    @Override
    public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO roleDTO) throws Exception
    {
        log.debug("REST request to update {} : {}", entityName, roleDTO);
        if (roleDTO.getId() == null)
        {
            return create(roleDTO);
        }
        roleDTO = roleService.update(roleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(entityName, roleDTO.getId().toString())).body(roleDTO);

    }

    /**
     * 逻辑删除角色，并物理删除角色菜单信息
     *
     * @param id
     * @return
     */
    @Override
    public <ID extends Serializable> ResponseEntity<ID> freeze(@PathVariable ID id) throws Exception
    {
        log.debug("REST request to freeze {} : {}", entityName, id);
        RoleDTO vo = new RoleDTO();
        vo.setId(id.toString());
        this.getService().freezeAndDeleteRelation(vo);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Page<RoleDTO>> query(@RequestParam String query, @ApiParam(required = true) Pageable pageable) throws Exception
    {
        log.debug("REST request to search for a page of {} for query {}", entityName, query);
        Page<RoleDTO> page = getService().findAll(y.util().json().jsonToObject(query, RoleDTO.class), pageable);
        roleService.setMenus4RoleDTOList(page.getContent());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    /**
     * 查询除系统之外的所有角色
     *
     * @return
     */
    @GetMapping("/normal")
    public ResponseEntity<List<RoleDTO>> getAll4Normal()
    {
        log.debug("REST request to get all for normal {}", entityName);
        return ResponseEntity.ok(roleService.findAll4Normal());
    }

}
