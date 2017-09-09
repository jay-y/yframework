package org.yframework.toolkit;

import java.nio.charset.Charset;

/**
 * 字符集枚举类<BR>
 * ------------------------------------------<BR>
 * <BR>
 * Copyright©  : 2014-2015 by Flying_L<BR>
 * Author      : Flying_L <BR>
 * Date        : 2014-11-8<BR>
 * Description :<BR>
 * <p>
 * 包含常用的编码格式
 * </p>
 */
public enum CharsetEnum
{
    UTF_8("UTF-8", "国际UNICODE编码"), GBK("GBK", "中文编码"), GB2312("GB2312", "中文编码"), ISO_8859_1("ISO-8859-1", "国际编码");

    /**
     * 关键字
     */
    private String key = null;

    /**
     * 字符集
     */
    private Charset charset = null;

    /**
     * 说明
     */
    private String description = null;

    /**
     * 获取字符集
     *
     * @param key
     * @return
     */
    public static CharsetEnum get(String key)
    {
        for (CharsetEnum enm : CharsetEnum.values())
        {
            if (enm.getKey().equals(key.toUpperCase()))
            {
                return enm;
            }
        }
        return null;
    }

    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
        this.charset = Charset.forName(key);
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @param key
     * @param description
     */
    CharsetEnum(String key, String description)
    {
        this.setKey(key);
        this.description = description;
    }

    /**
     * 获取字符集
     *
     * @return
     */
    public Charset getCharset()
    {
        return this.charset;
    }

    @Override
    public String toString()
    {
        return this.getKey();
    }
}
