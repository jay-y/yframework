package org.yframework.mybatis.auditing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.yframework.mybatis.auditing.domain.FieldObject;
import org.yframework.toolkit.StringUtil;
import org.yframework.toolkit.y;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: MapCacheUtil.<br>
 * Date: 2017/9/8 17:22<br>
 * Author: ysj
 */
public enum AuditingEntityCtrl
{
    INSTANCE;
    private static final Object _LOCK = new Object();
    private final Logger log = LoggerFactory.getLogger(AuditingEntityCtrl.class);
    private final Map<String, Table> tableMap = new ConcurrentHashMap<>();
    private final Map<String, Set<Field>> fieldsMap = new ConcurrentHashMap<>();
    private final Map<String, FieldObject> fieldObjMap = new ConcurrentHashMap<>();
    private final Map<String, String> colMap = new ConcurrentHashMap<>();
    private AuditListener auditListener;

    public void setAuditListener(AuditListener auditListener)
    {
        this.auditListener = auditListener;
        log.debug("Register audit listener successfully");
    }

    public AuditListener getAuditListener()
    {
        Assert.notNull(auditListener, "Audit listener can not be null");
        return auditListener;
    }

    public Map<String, Table> getTableMap()
    {
        return tableMap;
    }

    public Map<String, Set<Field>> getFieldsMap()
    {
        return fieldsMap;
    }

    public Map<String, FieldObject> getFieldObjMap()
    {
        return fieldObjMap;
    }

    public Map<String, String> getColMap()
    {
        return colMap;
    }

    public Table getTable(Class<?> cls)
    {
        String key = cls.getName();
        Table table = getTableMap().get(key);
        if (null == table)
        {
            table = cls.getAnnotation(Table.class);
            if (null != table)
            {
                getTableMap().put(key, table);
            }
        }
        return table;
    }

    public String getCol(String property)
    {
        String col = getColMap().get(property);
        if (StringUtil.isEmpty(col))
        {
            col = y.util().string().toUnderScoreCase(property);
            colMap.put(property, col);
        }
        return col;
    }

    public <O extends Object> Set<Field> getAllFields(O obj)
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

    public <O extends Object> FieldObject getFieldObj(Field field, O obj)
    {
        String key = this.formatKey(obj.getClass().getName(), field.getName());
        FieldObject fieldObject = getFieldObjMap().get(key);
        if (null == fieldObject)
        {
            String name = field.getName();
            Object val = y.util().reflection().getFieldValue(obj, field.getName());
            Class<?> type;
            if (null != val)
            {
                type = val.getClass();
            }
            else
            {
                type = field.getType();
            }
            fieldObject = new FieldObject(type, name, val);
            getFieldObjMap().put(key, fieldObject);
        }
        return fieldObject;
    }

    private String formatKey(String clsName, String fieldName)
    {
        return clsName + "-" + fieldName;
    }
}
