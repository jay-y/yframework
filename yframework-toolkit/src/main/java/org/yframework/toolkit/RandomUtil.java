package org.yframework.toolkit;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * Description: RandomUtil.<br>
 * 继承org.apache.commons.lang3.RandomStringUtils<br>
 * Date: 2017/9/7 20:00<br>
 * Author: ysj
 */
public class RandomUtil extends RandomStringUtils
{
    private static RandomUtil instance = null;

    static
    {
        instance = new RandomUtil();
    }

    private RandomUtil()
    {
    }

    public static RandomUtil getInstance()
    {
        return instance;
    }

    public String uuid()
    {
        return uuid(false);
    }

    public String uuid(boolean isPretty)
    {
        String uuid = UUID.randomUUID().toString();
        return isPretty ? uuid : uuid.replace("-", "");
    }
}
