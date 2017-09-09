package org.yframework.system.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;
import org.yframework.system.service.UserService;
import org.yframework.system.service.dto.UserDTO;

/**
 * Description: TestResource.<br>
 * Date: 2017/9/7 00:34<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/api/test")
public class TestResource extends AbstractAuditingEntityResource<UserDTO>
{
    private final Logger log = LoggerFactory.getLogger(TestResource.class);

    private final UserService userService;

    private final CacheManager cacheManager;

    public TestResource(UserService userService, CacheManager cacheManager)
    {
        this.userService = userService;
        this.cacheManager = cacheManager;
    }

    @Override
    public UserService getService()
    {
        return userService;
    }

    @GetMapping(value = "/login/{login}")
    public ResponseEntity<UserDTO> getByLogin(@PathVariable String login)
    {
        return ResponseEntity.ok().body(getService().findOneByLogin(login));
    }
}
