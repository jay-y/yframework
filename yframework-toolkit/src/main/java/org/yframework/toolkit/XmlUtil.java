package org.yframework.toolkit;

import java.util.Map;

/**
 * Description: XmlUtil.<br>
 * Date: 2016/4/7 18:05 <br>
 * Author: ysj
 */
public interface XmlUtil
{
    <T> String toXml(T object);

    <T> String toPrettyXml(T object);

    <T> T xmlToObject(byte[] content, Class<T> cls);

    /**
     * XML字符串转对象
     *
     * @param content
     * @param cls
     * @param <T>
     * @return 实体
     */
    <T> T xmlToObject(String content, Class<T> cls);

    /**
     * XML字符串转换成Map
     *
     * @param content
     * @return 集合
     */
    <T> Map<String, T> xmlToMap(String content);
}
