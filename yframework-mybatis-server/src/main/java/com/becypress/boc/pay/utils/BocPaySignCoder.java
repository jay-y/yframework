package com.becypress.boc.pay.utils;

import com.becypress.sign.utils.SignCoder;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Description: BocSignCoder.<br>
 * Date: 2017/5/25 11:28<br>
 * Author: ysj
 */
public enum BocPaySignCoder implements SignCoder
{
    INSTANCE;

    public String encode(String origin, String charset)
    {
        String result = null;
        try
        {
            //签名串使用SHA-1做摘要, 摘要结果转16进制
            result = DigestUtils.md5Hex(origin.getBytes(charset));
        }
        catch (UnsupportedEncodingException e)
        {
        }
        return result;
    }
}
