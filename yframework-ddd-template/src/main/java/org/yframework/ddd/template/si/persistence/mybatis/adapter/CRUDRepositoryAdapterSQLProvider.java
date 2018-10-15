package org.yframework.ddd.template.si.persistence.mybatis.adapter;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.yframework.ddd.template.si.persistence.mybatis.config.AggregateRootConstants;
import org.yframework.ddd.template.si.persistence.mybatis.config.PropertyTypeEnum;
import org.yframework.ddd.template.si.persistence.mybatis.config.SQLOperationEnum;
import org.yframework.ddd.template.si.persistence.mybatis.model.CommonAggregateRoot;
import org.yframework.ddd.template.si.persistence.mybatis.model.Datatable;
import org.yframework.ddd.template.si.persistence.mybatis.model.Property;
import org.yframework.toolkit.y;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: CRUDRepositoryAdapterSQLProvider.<br>
 * Date: 2018/10/12 下午2:24<br>
 * Author: ysj
 */
@Component
public class CRUDRepositoryAdapterSQLProvider
{
    public static final String _METHOD_CREATE = "create";
    public static final String _METHOD_RETRIEVE = "retrieve";
    public static final String _METHOD_RETRIEVE_PAGE = "retrievePage";
    public static final String _METHOD_UPDATE = "update";
    public static final String _METHOD_DELETE = "delete";

    public static final String _PARAME_KEY_DO = "_do";
    public static final String _PARAME_KEY_PAGEABLE = "_pageable";

    private static final String _SQL_COUNT = " COUNT(1) ";
    private static final String _SQL_SELECT = " SELECT ";
    private static final String _SQL_DELETE = " DELETE ";
    private static final String _SQL_FROM = " FROM ";
    private static final String _SQL_AS = " AS ";

    private static final String _PATTERN_DATE = "yyyy-MM-dd HH:mm:ss";

    private static final Object _LOCK = new Object();

    private static final Map<String, Datatable> _DATATABLES = new HashMap<>();

    public String create(CommonAggregateRoot t)
    {
        t.setCreatedDate(this.getSystemTime());
        return this.buildInsertSQL(t);
    }

    public String retrieve(CommonAggregateRoot t)
    {
        return this.buildSelectSQL(t);
    }

    public String update(CommonAggregateRoot t)
    {
        t.setLastModifiedDate(this.getSystemTime());
        return this.buildUpdateSQL(t);
    }

    public String delete(CommonAggregateRoot t)
    {
        return this.buildDeleteSQL(t);
    }

    protected String buildInsertSQL(CommonAggregateRoot t)
    {
        Datatable datatable = this.getDatatable(t);
        SQL sql = new SQL().INSERT_INTO(datatable.getName());
        datatable.getProperties().forEach(field -> {
            String col = field.getName();
            String param = this.getParameter(field.getAlias());
            Object val = t.get(col);
            if (null != val)
            {
                sql.INTO_COLUMNS(col).INTO_VALUES(param);
            }
        });
        return sql.toString();
    }

    protected String buildUpdateSQL(CommonAggregateRoot t)
    {
        Datatable datatable = this.getDatatable(t);
        SQL sql = new SQL().UPDATE(datatable.getName());
        datatable.getProperties().forEach(field -> {
            String col = field.getName();
            Object val = t.get(col);
            if (null != val)
            {
                String cond = this.getCondition(col, field, val);
                if (AggregateRootConstants._KEY_ID.equals(field.getName()))
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

    protected String buildSelectSQL(CommonAggregateRoot t)
    {
        Datatable datatable = this.getDatatable(t);
        SQL sql = new SQL().FROM(datatable.getName());
        datatable.getProperties().forEach(field -> {
            String col = field.getName();
            Object val = t.get(col);
            sql.SELECT(col);
            if (null != val)
            {
                String cond = this.getCondition(col, field, val);
                sql.WHERE(cond);
            }
        });
        return sql.toString();
    }

    protected String buildSelectSQL(CommonAggregateRoot t, Pageable pageable)
    {
        Datatable datatable = this.getDatatable(t);
        SQL sql = new SQL().FROM(datatable.getName());
        if (null != pageable)
        {
            int pageNum = pageable.getPageNumber();
            int pageSize = pageable.getPageSize();
            this.buildLimitSQL(sql, pageNum, pageSize);
            this.buildOrdersSQL(sql, pageable.getSort());
        }
        datatable.getProperties().forEach(field -> {
            final String col = field.getName();
            final Object val = t.get(col);
            sql.SELECT(col);
            if (null != val)
            {
                if (null != field.getRoles() && field.getRoles().size() > 0)
                {
                    field.getRoles().forEach(o -> {
                        String cond = this.getCondition(col, field, val, SQLOperationEnum.get(o));
                        sql.WHERE(cond);
                    });
                }
                else
                {
                    String cond = this.getCondition(col, field, val, null);
                    sql.WHERE(cond);
                }
            }
        });
        return sql.toString();
    }

    protected String buildDeleteSQL(CommonAggregateRoot t)
    {
        Datatable datatable = this.getDatatable(t);
        SQL sql = new SQL().DELETE_FROM(datatable.getName());
        sql = this.buildWhereSQL(datatable, sql, t);
        return sql.toString();
    }

    protected String builCountSQL(CommonAggregateRoot t)
    {
        Datatable datatable = this.getDatatable(t);
        SQL sql = new SQL().SELECT(_SQL_COUNT).FROM(datatable.getName());
        sql = this.buildWhereSQL(datatable, sql, t);
        return sql.toString();
    }

    protected SQL buildWhereSQL(Datatable datatable, SQL sql, CommonAggregateRoot t)
    {
        datatable.getProperties().forEach(field -> {
            String col = field.getName();
            Object val = t.get(col);
            if (null != val)
            {
                String cond = this.getCondition(col, field, val);
                sql.WHERE(cond);
            }
        });
        return sql;
    }

    protected SQL buildOrdersSQL(SQL sql, Sort sort)
    {
        sort.spliterator().
                forEachRemaining(order -> {
                    sql.ORDER_BY(order.getProperty() + " " + order.getDirection());
                });
        return sql;
    }

    protected SQL buildLimitSQL(SQL sql, int pageNum, int pageSize)
    {
        //TODO 暂时不做编码，使用插件
        return sql;
    }

    protected String getCondition(String col, Property property, Object val)
    {
        return this.getCondition(col, property, val, SQLOperationEnum.EQ);
    }

    protected String getCondition(String col, Property field, Object val, SQLOperationEnum operator)
    {
        String param = this.getParameter(field.getAlias());
        StringBuilder sql = new StringBuilder();
        operator = null != operator ? operator : SQLOperationEnum.LK;
        if (SQLOperationEnum.LK.equals(operator))
        {
            if (!PropertyTypeEnum.DATE.getName().equals(field.getType()))
            {
                String param1 = "CONCAT(\'%\', CONCAT(" + param + ", \'%\'))";
                sql.append(col);
                sql.append(operator.getVaule());
                sql.append(param1);
            }
            else
            {
                String date;
                if (val instanceof Instant)
                {
                    date = y.util().time().get((Instant) val, "yyyy-MM-dd");
                }
                else
                {
                    date = y.util().time().get((Date) val, "yyyy-MM-dd");
                }

                if (StringUtils.isNotBlank(date))
                {
                    String startDate = "\'" + date + " 00:00:00\'";
                    String endDate = "\'" + date + " 23:59:59\'";
                    sql.append(col);
                    sql.append(" BETWEEN ");
                    sql.append(startDate);
                    sql.append(" AND ");
                    sql.append(endDate);
                }
            }
        }
        else
        {
            sql.append(col);
            sql.append(operator.getVaule());
            sql.append(param);
        }
        return sql.toString();
    }

    private Datatable getDatatable(CommonAggregateRoot t)
    {
        Datatable datatable = _DATATABLES.get(t.getResourceId());
        if (null == datatable)
        {
            synchronized (_LOCK)
            {
                datatable = _DATATABLES.get(t.getResourceId());
                if (null == datatable)
                {
                    datatable = new Datatable(t.getResourceId());
                    datatable = datatable.readConf(t.getLocation(), t.getCharset());
                    _DATATABLES.put(t.getResourceId(), datatable);
                }
            }
        }
        return datatable;
    }

    private String getSystemTime()
    {
        return y.util().time().now(_PATTERN_DATE);
    }

    private String getParameter(String alias)
    {
        return "#{" + alias + "}";
    }
}
