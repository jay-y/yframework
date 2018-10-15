package com.cust.system.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cust.system.service.UserService;
import com.cust.system.service.dto.UserDTO;
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
@RequestMapping("/mgt/users")
public class UserResource extends AbstractAuditingEntityResource<UserDTO>
{
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    private static final String entityName = "User";

    private final UserService userService;

    public UserResource(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public UserService getService()
    {
        return userService;
    }


    @Override
    public <ID extends Serializable> ResponseEntity<UserDTO> get(@PathVariable ID id) throws Exception
    {
        log.debug("REST request to get one {} : {}", entityName, id);
        UserDTO vo = new UserDTO();
        vo.setId(String.valueOf(id));
        vo = this.getService().findOne(vo);
        return ResponseEntity.ok(vo);
    }

    @Override
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) throws Exception
    {
        log.debug("REST request to save {} : {}", entityName, userDTO);
        if (userDTO.getId() != null)
        {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(entityName, "idexists", "A new vo cannot already have an ID")).body(null);
        }

        userDTO = userService.create(userDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(entityName, userDTO.getId().toString())).body(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) throws Exception
    {
        log.debug("REST request to update {} : {}", entityName, userDTO);
        if (userDTO.getId() == null)
        {
            return create(userDTO);
        }

        userDTO = userService.update(userDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(entityName, userDTO.getId().toString())).body(userDTO);
    }

    /**
     * 逻辑删除用户，并物理删除用户角色信息
     *
     * @param login
     * @return
     */
    @DeleteMapping("/delete/{login}")
    @Timed
    public ResponseEntity<Void> delete4Login(@PathVariable String login)
    {
        log.debug("REST request to delete with login: {}", login);

        userService.deleteWithLogin(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted", login)).build();
    }

    /**
     * 查询所有用户（包含角色信息）
     *
     * @return
     */
    @Override
    public ResponseEntity<List<UserDTO>> all()
    {
        return ResponseEntity.ok(userService.getAllWithRoles());
    }

    @Override
    public ResponseEntity<Page<UserDTO>> query(@RequestParam String query, @ApiParam(required = true) Pageable pageable) throws Exception
    {
        log.debug("REST request to search for a page of {} for query {}", entityName, query);
        Page<UserDTO> page = getService().findAll(y.util().json().jsonToObject(query, UserDTO.class), pageable);
        userService.setRoles4UserDTOList(page.getContent());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page);
    }
}
