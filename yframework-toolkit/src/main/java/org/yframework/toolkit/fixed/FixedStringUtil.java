package org.yframework.toolkit.fixed;

import org.yframework.toolkit.FixedUtil;
import org.yframework.toolkit.fixed.annotation.FixedStringProperty;
import org.yframework.toolkit.y;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Description: FixedStringUtil.<br>
 * Date: 2017/8/24 16:42<br>
 * Author: ysj
 */
public enum FixedStringUtil implements FixedUtil
{
    INSTANCE;
    private final Map<String, List<Field>> fieldListMap = new HashMap<>();
    private final Map<String, Field[]> fieldsMap = new HashMap<>();

    @Override
    public <T> String toFixed(T object, Charset charset)
    {
        try
        {
            StringBuilder str = new StringBuilder();
            List<Field> fieldList = getFieldList(object.getClass());
            fieldList.stream().
                    filter(field -> field.isAnnotationPresent(FixedStringProperty.class)).
                    sorted(this.getFieldComparator()).
                    forEach(field ->
                    {
                        FixedStringProperty p = field.getAnnotation(FixedStringProperty.class);
                        String value = (String) y.util().reflection().getFieldValue(object, field.getName());
                        boolean isTrimLeft = false;
                        switch (p.align())
                        {
                            case A:
                            case L:
                                isTrimLeft = false;
                                break;
                            case R:
                                isTrimLeft = true;
                                break;
                        }
                        String s = y.util().string().padding(value, charset, p.length(), p.fill(), isTrimLeft, p.isCut());
                        str.append(s);
                    });
            return str.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T fixedToObject(String content, T object, Charset charset)
    {
        try
        {
            List<Field> fieldList = getFieldList(object.getClass());
            fieldList.stream().
                    filter(field -> field.isAnnotationPresent(FixedStringProperty.class)).
                    sorted(this.getFieldComparator()).
                    forEach(field ->
                    {
                        FixedStringProperty p = field.getAnnotation(FixedStringProperty.class);
                        String s = y.util().string().substring(content, charset, p.index(), p.length()).trim();
                        y.util().reflection().setFieldValue(object, field.getName(), s);
                    });
            return object;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T fixedToObject(String content, Class<T> cls, Charset charset)
    {
        try
        {
            T t = cls.newInstance();
            List<Field> fieldList = getFieldList(cls);
            fieldList.stream().
                    filter(field -> field.isAnnotationPresent(FixedStringProperty.class)).
                    sorted(this.getFieldComparator()).
                    forEach(field ->
                    {
                        FixedStringProperty p = field.getAnnotation(FixedStringProperty.class);
                        String s = y.util().string().substring(content, charset, p.index(), p.length()).trim();
                        y.util().reflection().setFieldValue(t, field.getName(), s);
                    });
            return t;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<Field> getFieldList(Class<T> cls)
    {
        String key = cls.getName();
        List<Field> fieldList = fieldListMap.get(key);
        if (null == fieldList)
        {
            Field[] fields = cls.getDeclaredFields();
            fieldList = Arrays.asList(fields);
            fieldListMap.put(key, fieldList);
        }
        return fieldList;
    }

    private Comparator<Field> getFieldComparator()
    {
        return (o1, o2) ->
        {
            FixedStringProperty p1 = o1.getAnnotation(FixedStringProperty.class);
            FixedStringProperty p2 = o2.getAnnotation(FixedStringProperty.class);
            if (p1.index() > p2.index())
            {
                return 1;
            }
            else if (p1.index() < p2.index())
            {
                return -1;
            }
            return 0;
        };
    }
}
