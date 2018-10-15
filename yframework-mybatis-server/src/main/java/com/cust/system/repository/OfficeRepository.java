package com.cust.system.repository;

import com.cust.system.domain.Office;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

/**
 * Author: zk
 */
@Mapper
public interface OfficeRepository extends AuditingEntityRepository<Office>
{
}
