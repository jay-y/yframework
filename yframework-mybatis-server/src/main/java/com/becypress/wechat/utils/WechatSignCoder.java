package com.becypress.wechat.utils;

import com.becypress.sign.utils.SignCoder;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Description: WechatSignCoder.<br>
 * Date: 2018/3/1 下午7:19<br>
 * Author: ysj
 */
public enum WechatSignCoder implements SignCoder
{
    INSTANCE;

    public String encode(String origin, String charset)
    {
        String result = null;
        try
        {
            //签名串使用MD5做摘要, 摘要结果转16进制
            result = DigestUtils.md5Hex(origin.getBytes(charset));
        }
        catch (UnsupportedEncodingException e)
        {
        }
        return result;
    }
}
