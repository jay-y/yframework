package org.yframework.toolkit;

import java.nio.charset.Charset;

/**
 * Description: FixedUtil.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public interface FixedUtil
{
    <T> String toFixed(T object, Charset charset);

    <T> T fixedToObject(String content, T object, Charset charset);

    <T> T fixedToObject(String content, Class<T> cls, Charset charset);
}
