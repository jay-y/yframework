package org.yframework.mybatis.auditing.repository;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.yframework.mybatis.auditing.annotation.Condition;
import org.yframework.mybatis.auditing.domain.AuditingEntity;
import org.yframework.mybatis.auditing.domain.FieldObject;
import org.yframework.mybatis.auditing.utils.AuditingEntityCtrl;
import org.yframework.toolkit.StringUtil;
import org.yframework.toolkit.y;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * Description: AuditingEntitySqlProvider.<br>
 * Date: 2017/9/7 17:04<br>
 * Author: ysj
 */
public class AuditingEntitySqlProvider<E extends AuditingEntity<ID>, ID extends Serializable>
{
    public static final String _METHOD_INSERT = "insert";
    public static final String _METHOD_UPDATE = "update";

    public static final String _METHOD_FIND_ONE = "findOne";
    public static final String _METHOD_FIND_ONE_BY_ID = "findOneById";
    public static final String _METHOD_FIND_ALL = "findAll";
    public static final String _METHOD_FIND_LIST = "findList";
    public static final String _METHOD_FIND_PAGE = "findPage";

    public static final String _METHOD_COUNT = "count";
    public static final String _METHOD_COUNT_WITH_ENTITY = "countWithEntity";

    public static final String _METHOD_ACTIVATE = "activate";

    public static final String _METHOD_FREEZE = "freeze";

    public static final String _METHOD_DELETE = "delete";
    public static final String _METHOD_DELETE_ALL = "deleteAll";

    public static final String _PARAME_KEY_DO = "_do";
    public static final String _PARAME_KEY_PAGEABLE = "_pageable";

    private static final String _SQL_COUNT = " COUNT(1) ";
    private static final String _SQL_SELECT = " SELECT ";
    private static final String _SQL_DELETE = " DELETE ";
    private static final String _SQL_FROM = " FROM ";

    public String insert(E entity)
    {
        entity.setCreatedBy(this.getCurrentAuditor());
        entity.setCreatedDate(Instant.now());
        return this.buildInsertSQL(entity);
    }

    public String update(E entity)
    {
        entity.setLastModifiedBy(this.getCurrentAuditor());
        entity.setLastModifiedDate(Instant.now());
        return this.buildUpdateSQL(entity);
    }

    public String findOne(E entity)
    {
        return this.buildQuerySQL(entity);
    }

    public String findOneById(E entity)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().FROM(table.name());
        sql = this.buildWhereIdsSQL(sql, entity);
        return sql.toString();
    }

    public String findAll(Class<E> cls)
    {
        Table table = getTable(cls);
        return _SQL_SELECT + "*" + _SQL_FROM + table.name();
    }

    public String findList(E entity)
    {
        return this.buildQuerySQL(entity);
    }

    public String findPage(Map<String, Object> params)
    {
        E entity = (E) params.get(_PARAME_KEY_DO);
        Pageable pageable = (Pageable) params.get(_PARAME_KEY_PAGEABLE);
        return this.buildQuerySQL(entity, pageable);
    }

    public String count(Class<E> cls)
    {
        Table table = getTable(cls);
        return _SQL_SELECT + _SQL_COUNT + _SQL_FROM + table.name();
    }

    public String countWithEntity(E entity)
    {
        return this.builCountSQL(entity);
    }

    public String activate(E entity)
    {
        entity.setActivated(true);
        return this.update(entity);
    }

    public String freeze(E entity)
    {
        entity.setActivated(false);
        return this.update(entity);
    }

    public String delete(E entity)
    {
        return this.buildDeleteSQL(entity);
    }

    public String deleteAll(Class<E> cls)
    {
        Table table = getTable(cls);
        return _SQL_DELETE + _SQL_FROM + table.name();
    }

    private String buildInsertSQL(E entity)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().INSERT_INTO(table.name());
        Set<Field> fieldSet = getAllFields(entity);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(Column.class)).
                forEach(field ->
                {
                    Column column = field.getAnnotation(Column.class);
                    FieldObject fieldObject = getFieldObj(field, entity);
                    String col = this.getCol(fieldObject, column);
                    String param = this.getParameter(fieldObject, null);
                    Object val = this.getVal(fieldObject, entity);
                    if (null != val)
                    {
                        sql.INTO_COLUMNS(col).INTO_VALUES(param);
                    }
                });
        return sql.toString();
    }

    private String buildUpdateSQL(E entity)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().UPDATE(table.name());
        Set<Field> fieldSet = getAllFields(entity);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(Column.class)).
                forEach(field ->
                {
                    Column column = field.getAnnotation(Column.class);
                    FieldObject fieldObject = getFieldObj(field, entity);
                    String col = this.getCol(fieldObject, column);
                    String param = this.getParameter(fieldObject, null);
                    Object val = this.getVal(fieldObject, entity);
                    if (null != val)
                    {
                        String cond = this.getCondition(col, param);
                        if (field.isAnnotationPresent(Id.class))
                        {
                            sql.WHERE(cond);
                        }
                        else
                        {
                            sql.SET(cond);
                        }
                    }
                });
        return sql.toString();
    }

    private String buildQuerySQL(E entity)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().FROM(table.name());
        Set<Field> fieldSet = getAllFields(entity);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(Column.class)).
                forEach(field ->
                {
                    Column column = field.getAnnotation(Column.class);
                    FieldObject fieldObject = getFieldObj(field, entity);
                    String col = this.getCol(fieldObject, column);
                    String param = this.getParameter(fieldObject, null);
                    Object val = this.getVal(fieldObject, entity);
                    sql.SELECT(col);
                    if (null != val)
                    {
                        String cond = this.getCondition(col, param);
                        sql.WHERE(cond);
                    }
                });
        return sql.toString();
    }

    private String buildQuerySQL(E entity, Pageable pageable)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().FROM(table.name());
        if (null != pageable)
        {
            int pageNum = pageable.getPageNumber();
            int pageSize = pageable.getPageSize();
            this.buildLimitSQL(sql, pageNum, pageSize);
            this.buildOrdersSQL(sql, pageable.getSort());
        }
        Set<Field> fieldSet = getAllFields(entity);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(Column.class)).
                forEach(field ->
                {
                    Column column = field.getAnnotation(Column.class);
                    Condition condition = field.getAnnotation(Condition.class);
                    FieldObject fieldObject = getFieldObj(field, entity);
                    String col = this.getCol(fieldObject, column);
                    String param = this.getParameter(fieldObject, _PARAME_KEY_DO);
                    Object val = this.getVal(fieldObject, entity);
                    sql.SELECT(col);
                    if (null != val)
                    {
                        String cond;
                        if (null != condition && !condition.like())
                        {
                            cond = this.getCondition(col, param);
                        }
                        else
                        {
                            cond = this.getLikeCondition(col, param);
                        }
                        sql.WHERE(cond);
                    }
                });
        return sql.toString();
    }

    private String buildDeleteSQL(E entity)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().DELETE_FROM(table.name());
        sql = this.buildWheresSQL(sql, entity);
        return sql.toString();
    }

    private String builCountSQL(E entity)
    {
        Table table = this.getTable(entity);
        SQL sql = new SQL().SELECT(_SQL_COUNT).FROM(table.name());
        sql = this.buildWheresSQL(sql, entity);
        return sql.toString();
    }

    private SQL buildWheresSQL(SQL sql, E entity)
    {
        Set<Field> fieldSet = getAllFields(entity);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(Column.class)).
                forEach(field ->
                {
                    Column column = field.getAnnotation(Column.class);
                    FieldObject fieldObject = getFieldObj(field, entity);
                    String col = this.getCol(fieldObject, column);
                    String param = this.getParameter(fieldObject, null);
                    Object val = this.getVal(fieldObject, entity);
                    if (null != val)
                    {
                        String cond = this.getCondition(col, param);
                        sql.WHERE(cond);
                    }
                });
        return sql;
    }

    private SQL buildWhereIdsSQL(SQL sql, E entity)
    {
        Set<Field> fieldSet = getAllFields(entity);
        fieldSet.stream().
                filter(field -> field.isAnnotationPresent(Column.class)).
                forEach(field ->
                {
                    FieldObject fieldObject = getFieldObj(field, entity);
                    Column column = field.getAnnotation(Column.class);
                    String col = this.getCol(fieldObject, column);
                    sql.SELECT(col);
                    if (field.isAnnotationPresent(Id.class))
                    {
                        String param = this.getParameter(fieldObject, null);
                        Object val = this.getVal(fieldObject, entity);
                        if (null != val)
                        {
                            String cond = this.getCondition(col, param);
                            sql.WHERE(cond);
                        }
                    }
                });
        return sql;
    }

    private SQL buildOrdersSQL(SQL sql, Sort sort)
    {
        sort.spliterator().
                forEachRemaining(order ->
                {
                    sql.ORDER_BY(AuditingEntityCtrl.INSTANCE.getCol(order.getProperty()) + " " + order.getDirection());
                });
        return sql;
    }

    private SQL buildLimitSQL(SQL sql, int pageNum, int pageSize)
    {
        //TODO 暂时不做编码，使用插件
        return sql;
    }

    private String getCol(FieldObject fieldObject, Column column)
    {
        return StringUtil.isNotEmpty(column.name()) ? column.name() : fieldObject.getName();
    }


    private String getParameter(FieldObject fieldObject, String aDo)
    {
        StringBuilder parameter = new StringBuilder("#{");
        if (StringUtil.isNotEmpty(aDo))
        {
            parameter.append(aDo).append(".");
        }
        parameter.append(fieldObject.getName());
        if (null != fieldObject.getType() && AuditingEntity.class.isInstance(fieldObject.getType()))
        {
            Set<Field> fieldSet = this.getAllFields(fieldObject.getType());
            Column column = fieldSet.stream().
                    filter(f -> f.isAnnotationPresent(Id.class)).
                    map(f -> f.getAnnotation(Column.class)).
                    findFirst().get();
            parameter.append(".");
            parameter.append(column.name());
        }
        return parameter.append("}").toString();
    }

    private Object getVal(FieldObject fieldObject, E entity)
    {
        Object val = y.util().reflection().getFieldValue(entity, fieldObject.getName());
        fieldObject.setValue(val);
        if (null != val)
        {
            if (val instanceof AuditingEntity)
            {
                val = ((AuditingEntity) val).getId();
            }
        }
        return val;
    }

    private String getCondition(String col, Object param)
    {
        return col + " = " + param;
    }

    private String getLikeCondition(String col, Object param)
    {
        String likeParam = "'%' || " + param + " || '%'";
        return col + " LIKE " + likeParam;
    }

    private Table getTable(E entity)
    {
        return AuditingEntityCtrl.INSTANCE.getTable(entity.getClass());
    }

    private Table getTable(Class<?> cls)
    {
        return AuditingEntityCtrl.INSTANCE.getTable(cls);
    }

    private <O extends Object> FieldObject getFieldObj(Field field, O obj)
    {
        return AuditingEntityCtrl.INSTANCE.getFieldObj(field, obj);
    }

    private <O extends Object> Set<Field> getAllFields(O obj)
    {
        return AuditingEntityCtrl.INSTANCE.getAllFields(obj);
    }

    private String getCurrentAuditor()
    {
        return AuditingEntityCtrl.INSTANCE.getAuditListener().getCurrentAuditor();
    }
}
