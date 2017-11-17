package org.yframework.mybatis.auditing.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Description: AuditingEntityDao.<br>
 * Date: 2017/9/7 16:52<br>
 * Author: ysj
 */
public interface AuditingEntityRepository<DO>
{
    @InsertProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_INSERT)
    @Options
    void insert(DO entity);

    @UpdateProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_UPDATE)
    void update(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_FIND_ONE)
    DO findOne(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_FIND_ONE_BY_ID)
    DO findOneById(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_FIND_ALL)
    List<DO> findAll(Class<DO> cls);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_FIND_LIST)
    List<DO> findList(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_FIND_PAGE)
    List<DO> findPage(@Param(value = AuditingEntitySqlProvider._PARAME_KEY_DO) DO entity, @Param(value = AuditingEntitySqlProvider._PARAME_KEY_PAGEABLE) Pageable pageable);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_COUNT)
    long count(Class<DO> cls);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_COUNT_WITH_ENTITY)
    long countWithEntity(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_ACTIVATE)
    void activate(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_FREEZE)
    void freeze(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_DELETE)
    void delete(DO entity);

    @SelectProvider(type = AuditingEntitySqlProvider.class, method = AuditingEntitySqlProvider._METHOD_DELETE_ALL)
    void deleteAll(Class<DO> cls);
}
