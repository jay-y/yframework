package com.cust.system.repository;

import com.cust.system.domain.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

import java.util.List;

/**
 * Author: zk
 */
@Mapper
public interface DictRepository extends AuditingEntityRepository<Dict>
{
    List<Dict> findAllByType(String type);
}
