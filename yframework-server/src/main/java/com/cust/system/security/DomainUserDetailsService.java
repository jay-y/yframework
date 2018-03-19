package com.cust.system.security;

import com.cust.system.domain.User;
import com.cust.system.repository.UserRepository;
import com.cust.system.repository.UserRoleRelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService
{

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    private final UserRoleRelationRepository userRoleRelationRepository;

    public DomainUserDetailsService(UserRepository userRepository, UserRoleRelationRepository userRoleRelationRepository)
    {
        this.userRepository = userRepository;
        this.userRoleRelationRepository = userRoleRelationRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login)
    {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        User userFromDatabase = getUserWithAuthorities(lowercaseLogin);
        if (userFromDatabase != null)
        {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (String roleId : userFromDatabase.getAuthorities())
            {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleId);
                grantedAuthorities.add(simpleGrantedAuthority);
            }
            return new org.springframework.security.core.userdetails.User(lowercaseLogin, userFromDatabase.getPassword(), grantedAuthorities);
        }
        else
        {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " + "database");
        }
    }

    public User getUserWithAuthorities(String login)
    {
        User user = userRepository.findUserByLogin(login);
        setRoles4User(user);
        return user;
    }

    public void setRoles4User(User user)
    {
        if (user != null)
        {
            List<String> rolesIdList = userRoleRelationRepository.findRolesIdByUserId(user.getId());
            user.setAuthorities(rolesIdList);
        }
    }
}
