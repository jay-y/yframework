package org.yframework.mybatis.auditing.generator;

import org.yframework.toolkit.RandomUtil;
import org.yframework.toolkit.y;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: IDGenerator.<br>
 * Date: 2017/9/7 19:47<br>
 * Author: ysj
 */
public enum IDGenerator implements Generator<String>
{
    UUID
        {
            @Override
            public String generate()
            {
                return y.util().random().uuid();
            }
        }, //生成32位UUID
    MD5
        {
            @Override
            public String generate()
            {
                try
                {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(UUID.generate().concat(RandomUtil.random(8)).getBytes());
                    return new BigInteger(1, md.digest()).toString(16);
                }
                catch (NoSuchAlgorithmException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }, //生成随机值的MD5值
    UUID22
        {
            @Override
            public String generate()
            {
                String uuidStr = java.util.UUID.randomUUID().toString();
                uuidStr = "0" + uuidStr.replaceAll("-", "").toUpperCase();
                StringBuilder result = new StringBuilder();
                int[] buff = new int[3];
                for (int i = 0; i < uuidStr.length(); i++)
                {
                    int index = i % 3;
                    buff[index] = Integer.parseInt("" + uuidStr.charAt(i), 16);
                    if (index == 2)
                    {
                        result.append(_BASE64_CHARS[buff[0] << 2 | buff[1] >>> 2]);
                        result.append(_BASE64_CHARS[(buff[1] & 3) << 4 | buff[2]]);
                    }
                }
                return result.toString();
            }
        }; //生成22位UUID

    private static final char[] _BASE64_CHARS;

    static
    {
        _BASE64_CHARS = new char[64];
        for (int i = 0; i < 10; i++)
        {
            _BASE64_CHARS[i] = (char) ('0' + i);
        }
        for (int i = 10; i < 36; i++)
        {
            _BASE64_CHARS[i] = (char) ('a' + i - 10);
        }
        for (int i = 36; i < 62; i++)
        {
            _BASE64_CHARS[i] = (char) ('A' + i - 36);
        }
        _BASE64_CHARS[62] = '_';
        _BASE64_CHARS[63] = '-';
    }
}
