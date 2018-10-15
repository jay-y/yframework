package com.cust.system.repository;

import com.cust.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

import java.util.List;

/**
 * Author: zk
 */
@Mapper
public interface RoleRepository extends AuditingEntityRepository<Role>
{
    List<Role> findRolesByUserId(String uid);

    Role findAllRolesByName(String name);

    Role findAllRolesByNameen(String nameen);
}
