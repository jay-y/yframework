package org.yframework.toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.asm.InvokerTool;
import org.yframework.toolkit.fixed.FixedStringUtil;
import org.yframework.toolkit.json.JacksonUtil;
import org.yframework.toolkit.task.TaskManager;
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

    public HttpUtil http()
    {
        return HttpUtil.INSTANCE;
    }

    public RSACrypto rsa()
    {
        return RSACrypto.INSTANCE;
    }

    public TimeUtil time()
    {
        return TimeUtil.INSTANCE;
    }

    public TaskManager task()
    {
        return TaskManager.INSTANCE;
    }

    public Miscellany misc()
    {
        return Miscellany.INSTANCE;
    }

    public CodogramUtil codogram()
    {
        return CodogramUtil.INSTANCE;
    }

    public ReflectionUtil reflection()
    {
        return ReflectionUtil.INSTANCE;
    }

    public FileUtil file()
    {
        return FileUtil.getInstance();
    }

    public StringUtil string()
    {
        return StringUtil.getInstance();
    }

    public InvokerTool invoker()
    {
        return InvokerTool.INSTANCE;
    }

    public FixedUtil fixed()
    {
        return FixedStringUtil.INSTANCE;
    }

    public FTPUtil ftp()
    {
        return FTPUtil.INSTANCE;
    }

    public RandomUtil random()
    {
        return RandomUtil.getInstance();
    }
}
