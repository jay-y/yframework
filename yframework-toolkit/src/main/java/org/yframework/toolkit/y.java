package org.yframework.toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.fixed.FixedStringUtil;
import org.yframework.toolkit.json.JacksonUtil;
import org.yframework.toolkit.xml.JacksonXmlUtil;

/**
 * Description: Toolkit Core.<br>
 * Date: 2017/7/4 10:15<br>
 * Author: ysj
 */
public enum y
{
    CORE;

    private final Logger log = LoggerFactory.getLogger(y.class);

    public static y core()
    {
        return CORE;
    }

    public static y util()
    {
        return CORE;
    }

    public JsonUtil json()
    {
        return JacksonUtil.INSTANCE;
    }

    public XmlUtil xml()
    {
        return JacksonXmlUtil.INSTANCE;
    }

    public TimeUtil time()
    {
        return TimeUtil.INSTANCE;
    }

    public StringUtil string()
    {
        return StringUtil.getInstance();
    }

    public FixedUtil fixed()
    {
        return FixedStringUtil.INSTANCE;
    }

    public FTPUtil ftp()
    {
        return FTPUtil.INSTANCE;
    }
}
