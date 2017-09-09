package org.yframework.system.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.yframework.mybatis.auditing.utils.AuditListener;
import org.yframework.system.config.Constants;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String>, AuditListener
{

    @Override
    public String getCurrentAuditor()
    {
        String userName = SecurityUtils.getCurrentUserLogin();
        return userName != null ? userName : Constants.SYSTEM_ACCOUNT;
    }
}
