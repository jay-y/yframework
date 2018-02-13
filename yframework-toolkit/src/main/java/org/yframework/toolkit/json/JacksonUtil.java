package org.yframework.toolkit.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.commons.lang3.StringUtils;
import org.yframework.toolkit.JsonUtil;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public enum JacksonUtil implements JsonUtil
{
    INSTANCE;

    private ObjectMapper objectMapper;

    JacksonUtil()
    {
        objectMapper = new ObjectMapper().
            registerModule(new ParameterNamesModule()).
            registerModule(new Jdk8Module()).
            registerModule(new JavaTimeModule());
        //设置null值不参与序列化(字段不被显示)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 对于空的对象转json的时候不抛出错误
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用序列化日期为timestamps
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 禁用遇到未知属性抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 视空字符传为null
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 低层级配置
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许属性名称没有引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 支持解析单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 支持解析结束符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public ObjectMapper get()
    {
        return objectMapper;
    }

    public String toJson(Object object)
    {
        String jsonString;
        try
        {
            jsonString = objectMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonString;
    }

    public String toPrettyJson(Object object)
    {
        String jsonString;
        try
        {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonString;

    }

    public <T> T jsonToObject(String json, Class<T> c)
    {
        try
        {
            if (StringUtils.isBlank(json))
            {
                return c.newInstance();
            }
            else
            {
                return objectMapper.readValue(json, c);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> cls)
    {
        return jsonToGenericObject(json, new TypeReference<List<T>>()
        {
        });
    }

    public List<Map<String, Object>> jsonToListMap(String json)
    {
        return jsonToGenericObject(json, new TypeReference<List<Map<String, Object>>>()
        {
        });
    }

    public Map<String, Object> jsonToMap(String json)
    {
        try
        {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return map;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private <T> T jsonToGenericObject(String json, TypeReference<T> tr)
    {
        if (StringUtils.isBlank(json))
        {
            return null;
        }
        else
        {
            try
            {
                return objectMapper.readValue(json, tr);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
