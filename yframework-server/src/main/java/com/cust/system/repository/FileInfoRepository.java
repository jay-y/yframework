package com.cust.system.repository;

import com.cust.system.domain.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

/**
 * Author: zk
 */
@Mapper
public interface FileInfoRepository extends AuditingEntityRepository<FileInfo>
{
}
