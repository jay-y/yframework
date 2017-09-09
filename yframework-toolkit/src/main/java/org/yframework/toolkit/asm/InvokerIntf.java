package org.yframework.toolkit.asm;

/**
 * 默认动态代理调度器<BR>
 * ------------------------------------------<BR>
 * <BR>
 * Copyright©  : 2014-2015 by Flying_L<BR>
 * Author      : Flying_L <BR>
 * Date        : 2014-11-3<BR>
 * Description :<BR>
 * <p>
 * 动态方法调度器，效率不及Invokers中其他调度方式，而且一定会返回一个对象
 * </p>
 */
public interface InvokerIntf
{
    /**
     * 调用方法
     *
     * @param host 执行对象
     * @param args 执行参数
     * @return
     */
    public Object invoke(Object host, Object... args);
}
