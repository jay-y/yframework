package org.yframework.system.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yframework.mybatis.auditing.web.rest.AbstractAuditingEntityResource;
import org.yframework.system.service.UserService;
import org.yframework.system.service.dto.UserDTO;

/**
 * Description: UserResource.<br>
 * Date: 2017/9/6 23:14<br>
 * Author: ysj
 */
@RestController
@RequestMapping("/api/users")
public class UserResource extends AbstractAuditingEntityResource<UserDTO>
{
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
}
