package com.cust.security.service;

import com.becypress.toolkit.spring.boot.config.Constants;
import com.cust.security.utils.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.yframework.mybatis.auditing.utils.AuditListener;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String>, AuditListener
{

    @Override
    public String getCurrentAuditor()
    {
        String userName = SecurityUtil.INSTANCE.getCurrentUserLogin();
        return userName != null ? userName : Constants.SYSTEM_ACCOUNT;
    }
}
