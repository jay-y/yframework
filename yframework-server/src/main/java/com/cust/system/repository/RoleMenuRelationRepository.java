package com.cust.system.repository;

import com.cust.system.domain.RoleMenuRelation;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

import java.util.List;

/**
 * Author: zk
 */
@Mapper
public interface RoleMenuRelationRepository extends AuditingEntityRepository<RoleMenuRelation>
{
    int save(RoleMenuRelation roleMenuRelation);

    int deleteByRoleId(String roleId);

    List<String> findMenusIdByRoleId(String rid);
}
