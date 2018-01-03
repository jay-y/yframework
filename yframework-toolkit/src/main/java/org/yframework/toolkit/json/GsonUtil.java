package org.yframework.toolkit.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.yframework.toolkit.JsonUtil;

import java.util.List;
import java.util.Map;

public enum GsonUtil implements JsonUtil
{
    INSTANCE;
    private Gson gson;

    GsonUtil()
    {
        gson = new Gson();
    }

    public Gson get()
    {
        return gson;
    }

    public String toJson(Object object)
    {
        String jsonString = null;
        if (gson != null)
        {
            jsonString = gson.toJson(object);
        }
        return jsonString;
    }

    public String toPrettyJson(Object object)
    {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    public <T> T jsonToObject(String JsonString, Class<T> cls)
    {
        T t = null;
        if (gson != null)
        {
            t = gson.fromJson(JsonString, cls);
        }
        return t;
    }

    public <T> List<T> jsonToList(String JsonString, Class<T> cls)
    {
        List<T> list = null;
        if (gson != null)
        {
            list = gson.fromJson(JsonString, new TypeToken<List<T>>()
            {
            }.getType());
        }
        return list;
    }

    public <T> List<Map<String, T>> jsonToListMap(String JsonString)
    {
        List<Map<String, T>> list = null;
        if (gson != null)
        {
            list = gson.fromJson(JsonString, new TypeToken<List<Map<String, T>>>()
            {
            }.getType());
        }
        return list;
    }

    public <T> Map<String, T> jsonToMap(String JsonString)
    {
        Map<String, T> map = null;
        if (gson != null)
        {
            map = gson.fromJson(JsonString, new TypeToken<Map<String, T>>()
            {
            }.getType());
        }
        return map;
    }
}
