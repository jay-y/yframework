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
            }; //生成随机值的MD5值
}
