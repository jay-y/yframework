package com.cust.system.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cust.system.domain.User;
import com.cust.system.service.AuthoritiesService;
import com.cust.system.service.UserService;
import com.cust.system.service.dto.UserDTO;
import com.cust.system.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource
{
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;

    private final UserMapper userMapper;

    private final AuthoritiesService authoritiesService;

    public AccountResource(UserService userService, UserMapper userMapper, AuthoritiesService authoritiesService)
    {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authoritiesService = authoritiesService;
    }

    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @GetMapping("/authenticate")
    @Timed
    public String isAuthenticated(HttpServletRequest request)
    {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET  /account : get the current user.
     *
     * @return the ResponseEntity with status 200 (OK) and the current user in body, or status 500 (Internal Server Error) if the user couldn't be returned
     */
    @GetMapping("/account")
    @Timed
    public ResponseEntity<UserDTO> getAccount()
    {
        User user = userService.getUserWithAuthorities();
        if (user != null)
        {
            return new ResponseEntity<>(userMapper.doToDto(user), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/account/save")
    @Timed
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) throws Exception
    {
        log.debug("REST request to save account : {}", userDTO);
        if (userDTO.getId() == null)
        {
            userDTO = userService.create(userDTO);
        }
        else
        {
            userDTO = userService.update(userDTO);
        }
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/account/reset")
    @Timed
    public ResponseEntity<UserDTO> resetPassWord(@RequestBody UserDTO userDTO)
    {
        log.debug("REST request to reset account : {}", userDTO);

        UserDTO resetUserDTO = userService.resetPassWord(userDTO);
        return ResponseEntity.ok(resetUserDTO);
    }
}
