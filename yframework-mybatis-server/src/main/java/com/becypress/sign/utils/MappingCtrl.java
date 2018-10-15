package com.becypress.sign.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: MappingCtrl.<br>
 * Date: 2017/9/8 17:22<br>
 * Author: ysj
 */
public enum MappingCtrl
{
    INSTANCE;
    private static final Object _LOCK = new Object();
    private final Map<String, Set<Field>> fieldsMap = new ConcurrentHashMap<>();

    public Map<String, Set<Field>> getFieldsMap()
    {
        return fieldsMap;
    }

    public Set<Field> getAllFields(Object obj)
    {
        String key = obj.getClass().getName();
        Set<Field> fieldSet = getFieldsMap().get(key);
        if (null == fieldSet || 0 == fieldSet.size())
        {
            synchronized (_LOCK)
            {
                if (null == fieldSet || 0 == fieldSet.size())
                {
                    fieldSet = new HashSet<>();
                    Class tmpClass = obj.getClass();
                    while (null != tmpClass && !tmpClass.getName().toLowerCase().equals("java.lang.object"))
                    {
                        Collections.addAll(fieldSet, tmpClass.getDeclaredFields());
                        tmpClass = tmpClass.getSuperclass();
                    }
                    getFieldsMap().put(key, fieldSet);
                }
            }
        }
        return fieldSet;
    }
}
