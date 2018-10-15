package com.becypress.toolkit;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

public enum AopUtil
{
    INSTANCE;

    /**
     * 获取 目标对象
     *
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public Object getTarget(Object proxy) throws Exception
    {
        if (!AopUtils.isAopProxy(proxy))//不是代理对象
        {
            return proxy;
        }

        if (AopUtils.isJdkDynamicProxy(proxy))//jdk
        {
            return getJdkDynamicProxyTargetObject(proxy);
        }
        else//cglib
        {
            return getCglibProxyTargetObject(proxy);
        }
    }

    private Object getCglibProxyTargetObject(Object proxy) throws Exception
    {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
    }


    private Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception
    {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        return ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
    }

}
