package com.becypress.toolkit;

import com.becypress.toolkit.spring.boot.SpringBootUtil;
import org.yframework.toolkit.y;

/**
 * Description: Becypress.<br>
 * Date: 2017/10/2 13:42<br>
 * Author: ysj
 */
public enum Becypress
{
    UTIL;

    public y extra()
    {
        return y.CORE;
    }

    public SpringBootUtil springboot()
    {
        return SpringBootUtil.INSTANCE;
    }

    public AopUtil aop()
    {
        return AopUtil.INSTANCE;
    }

    public ByteUtil bit()
    {
        return ByteUtil.INSTANCE;
    }

    public HttpUtil http()
    {
        return HttpUtil.INSTANCE;
    }
}
