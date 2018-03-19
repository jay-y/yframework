package com.cust.common.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * Aspect for logging execution of service and repository Spring components.
 * <p>
 * By default, it only runs with the "dev" profile.
 */
@Aspect
public class LoggingAspect extends AbstractLoggingAspect
{
    private static final String _PACKAGE_NAME = "com.cust";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public LoggingAspect(Environment env)
    {
        this.env = env;
    }

    @Override
    protected Logger getLogger()
    {
        return log;
    }

    @Override
    protected Environment getEnv()
    {
        return env;
    }

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" + " || within(@org.springframework.stereotype.Service *)" + " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut()
    {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(" + _PACKAGE_NAME + ".biz.repository..*)" + " || within(" + _PACKAGE_NAME + ".biz.service..*)" + " || within(" + _PACKAGE_NAME + ".biz.web.rest..*)" + " || within(" + _PACKAGE_NAME + ".biz.web.ctrl..*)" + " || within(" + _PACKAGE_NAME + ".system.repository..*)" + " || within(" + _PACKAGE_NAME + ".system.service..*)" + " || within(" + _PACKAGE_NAME + ".system.web.rest..*)" + " || within(" + _PACKAGE_NAME + ".system.web.ctrl..*)")
    public void applicationPackagePointcut()
    {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e)
    {
        this.logAfterThrowing(joinPoint, e);
    }

    /**
     * Advice that logs when a method is entered and exited.
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        return this.logAround(joinPoint);
    }
}