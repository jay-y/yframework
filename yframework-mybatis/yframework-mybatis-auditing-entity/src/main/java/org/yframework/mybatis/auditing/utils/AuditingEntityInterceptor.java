package org.yframework.mybatis.auditing.utils;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.yframework.mybatis.auditing.domain.AuditingEntity;
import org.yframework.mybatis.auditing.generator.IDGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

/**
 * Description: AuditingEntityInterceptor.<br>
 * Date: 2017/9/7 09:26<br>
 * Author: ysj
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class AuditingEntityInterceptor implements Interceptor, Serializable
{
    private static final long serialVersionUID = 1L;
    protected Log log = LogFactory.getLog(this.getClass());

    private final AuditListener listener;

    public AuditingEntityInterceptor(AuditListener listener)
    {
        this.listener = listener;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable
    {
        Arrays.stream(invocation.getArgs()).
                filter(o -> o instanceof AuditingEntity).
                forEach((Object o) ->
                {
                    AuditingEntity e = (AuditingEntity) o;
                    Instant now = Instant.now();
                    if (null == e.getId())
                    {
                        Set<Field> fieldSet = AuditingEntityCtrl.INSTANCE.getAllFields(o);
                        GeneratedValue generated = fieldSet.stream().
                                filter(field -> field.isAnnotationPresent(Id.class)).
                                map(field -> field.getAnnotation(GeneratedValue.class)).
                                findFirst().get();
                        e.setId(this.getId(generated));
                        e.setCreatedBy(this.listener.getCurrentAuditor());
                        e.setCreatedDate(now);
                    }
                    else
                    {
                        e.setLastModifiedBy(this.listener.getCurrentAuditor());
                        e.setLastModifiedDate(now);
                    }
                });
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o)
    {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties)
    {
    }

    private Serializable getId(GeneratedValue generated)
    {
        IDGenerator generator = IDGenerator.valueOf(generated.generator().toUpperCase());
        return generator.generate();
    }
}
