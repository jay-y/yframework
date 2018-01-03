package org.yframework.toolkit.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yframework.toolkit.XmlUtil;

import java.text.SimpleDateFormat;
import java.util.Map;

public enum JacksonXmlUtil implements XmlUtil
{
    INSTANCE;
    private final Logger log = LoggerFactory.getLogger(JacksonXmlUtil.class);

    private XmlMapper objectMapper;

    JacksonXmlUtil()
    {
        objectMapper = new XmlMapper();
        //设置null值不参与序列化(字段不被显示)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 对于空的对象转json的时候不抛出错误
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用遇到未知属性抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 视空字符传为null
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public XmlMapper get()
    {
        return objectMapper;
    }

    @Override
    public <T> String toXml(T object)
    {
        try
        {
            return objectMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public <T> String toPrettyXml(T object)
    {
        try
        {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public <T> T xmlToObject(byte[] content, Class<T> cls)
    {
        try
        {
            return objectMapper.readValue(content, cls);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public <T> T xmlToObject(String content, Class<T> cls)
    {
        try
        {
            return objectMapper.readValue(content, cls);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public <T> Map<String, T> xmlToMap(String content)
    {
        return xmlToObject(content, Map.class);
    }
}
