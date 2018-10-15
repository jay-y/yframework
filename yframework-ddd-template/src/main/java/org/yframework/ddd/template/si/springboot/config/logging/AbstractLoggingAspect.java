package org.yframework.ddd.template.si.springboot.config.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.yframework.ddd.template.si.springboot.config.Constants;

import java.util.Arrays;

/**
 * Description: AbstractLoggingAspect.<br>
 * Date: 2017/10/10 11:29<br>
 * Author: ysj
 */
public abstract class AbstractLoggingAspect
{
    protected abstract Logger getLogger();

    protected abstract Environment getEnv();

    protected void logAfterThrowing(JoinPoint joinPoint, Throwable e)
    {
        if (getEnv().acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT))
        {
            getLogger().error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);

        }
        else
        {
            getLogger().error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
        }
    }

    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable
    {
        if (getLogger().isDebugEnabled())
        {
            getLogger().debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try
        {
            Object result = joinPoint.proceed();
            if (getLogger().isDebugEnabled())
            {
                getLogger().debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result);
            }
            return result;
        }
        catch (IllegalArgumentException e)
        {
            getLogger().error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            throw e;
        }
    }
}
