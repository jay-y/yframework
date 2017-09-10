package org.yframework.mybatis.starter;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.yframework.mybatis.auditing.utils.AuditListener;
import org.yframework.mybatis.auditing.utils.AuditingEntityCtrl;
import org.yframework.mybatis.auditing.utils.AuditingEntityInterceptor;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnBean({SqlSessionFactory.class, AuditListener.class})
@AutoConfigureAfter({MybatisAutoConfiguration.class})
public class YMybatisAutoConfiguration implements EnvironmentAware
{
    private final Logger log = LoggerFactory.getLogger(YMybatisAutoConfiguration.class);

    private final SqlSessionFactory sqlSessionFactory;
    private final AuditListener listener;

    private RelaxedPropertyResolver resolver;

    public YMybatisAutoConfiguration(SqlSessionFactory sqlSessionFactory, AuditListener listener)
    {
        this.sqlSessionFactory = sqlSessionFactory;
        this.listener = listener;
        log.debug("Initialize yframework mybatis configuration");
    }

    public void setEnvironment(Environment environment)
    {
        this.resolver = new RelaxedPropertyResolver(environment, "yframework.mybatis.");
    }

    @PostConstruct
    public void addAuditingEntityInterceptor()
    {
        AuditingEntityCtrl.INSTANCE.setAuditListener(listener);
        AuditingEntityInterceptor interceptor = new AuditingEntityInterceptor(listener);
        this.sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        log.debug("Add auditing entity interceptor successfully");
    }
}
