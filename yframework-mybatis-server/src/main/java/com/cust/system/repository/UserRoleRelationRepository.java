package com.cust.system.repository;

import com.cust.system.domain.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

import java.util.List;

/**
 * Author: zk
 */
@Mapper
public interface UserRoleRelationRepository extends AuditingEntityRepository<UserRoleRelation>
{
    int save(UserRoleRelation userRoleRelation);

    int deleteByUserId(String userId);

    List<String> findRolesIdByUserId(String uid);

    List<UserRoleRelation> findAllUserRoleRelation();
}
