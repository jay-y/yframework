package org.yframework.ddd.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public enum ObjectUtil
{
    INSTANCE;

    private static final Object LOCK = new Object();
    private static final Map<String, Set<Field>> FIELD_MAP = new ConcurrentHashMap<>();

    ObjectUtil()
    {
    }

    public <T> Set<Field> getFieldSet(T t)
    {
        String key = t.getClass().getName();
        Set<Field> fieldSet = FIELD_MAP.get(key);
        if (fieldSet == null || fieldSet.size() == 0)
        {
            synchronized (LOCK)
            {
                if (null == fieldSet || 0 == fieldSet.size())
                {
                    fieldSet = new HashSet<>();

                    for (Class<?> tmpClass = t.getClass(); null != tmpClass && !tmpClass.getName().toLowerCase().equals("java.lang.object"); tmpClass = tmpClass.getSuperclass())
                    {
                        Collections.addAll(fieldSet, tmpClass.getDeclaredFields());
                    }

                    FIELD_MAP.put(key, fieldSet);
                }
            }
        }

        return fieldSet;
    }

    public <T> void cover(T source, T target)
    {
        Set<Field> fieldSet = this.getFieldSet(target);
        if (fieldSet != null && fieldSet.size() > 0)
        {
            fieldSet.forEach((field) -> {
                try
                {
                    field.setAccessible(true);
                    if (!Modifier.isFinal(field.getModifiers()))
                    {
                        field.set(target, field.get(source));
                    }
                }
                catch (IllegalArgumentException | IllegalAccessException ignored)
                {
                }
            });
        }
    }

    public <T> boolean exist(T target, T temp)
    {
        if (target != null && temp != null)
        {
            this.cover(temp, target);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void populate(Object target, Map<String, ?> temp)
    {
        if (target != null && temp != null)
        {
            Set<Field> fieldSet = this.getFieldSet(target);
            if (fieldSet != null && fieldSet.size() > 0)
            {
                Map<String, Field> fieldMap = fieldSet.stream().collect(Collectors.toMap(Field::getName, (f) -> f));
                temp.forEach((key, value) -> {
                    try
                    {
                        Field field = fieldMap.get(key);
                        if (field != null)
                        {
                            field.setAccessible(true);
                            field.set(target, value);
                        }
                    }
                    catch (IllegalArgumentException | IllegalAccessException ignored)
                    {
                    }
                });
            }
        }
    }

    public Map<String, Object> toMap(Object source)
    {
        return Optional.ofNullable(source).
                map(o -> {
                    Map<String, Object> target = new LinkedHashMap<>();
                    Optional.ofNullable(this.getFieldSet(o)).
                            map(fields -> fields.stream().collect(Collectors.toMap(Field::getName, (f) -> f))).
                            orElse(new LinkedHashMap<>()).
                            forEach((key, value) -> {
                                try
                                {
                                    if (value != null)
                                    {
                                        value.setAccessible(true);
                                        target.put(key, value.get(source));
                                    }
                                }
                                catch (IllegalArgumentException | IllegalAccessException ignored)
                                {
                                }
                            });
                    return target;
                }).
                orElse(null);
    }
}
