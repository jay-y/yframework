package com.cust.system.repository;

import com.cust.system.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

import java.util.List;

/**
 * Author: zk
 */
@Mapper
public interface MenuRepository extends AuditingEntityRepository<Menu>
{
    List<Menu> findAllMenus();

    List<Menu> findMenusByRoleId(String roleId);
}
