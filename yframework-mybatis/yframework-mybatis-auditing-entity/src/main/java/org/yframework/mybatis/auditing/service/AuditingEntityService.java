package org.yframework.mybatis.auditing.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yframework.mybatis.auditing.domain.AuditingEntity;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;
import org.yframework.mybatis.auditing.service.dto.AuditingEntityDTO;
import org.yframework.mybatis.auditing.service.mapper.AuditingEntityMapper;

import java.util.List;

/**
 * Description: AuditingEntityService.<br>
 * Date: 2017/9/8 14:28<br>
 * Author: ysj
 */
public interface AuditingEntityService<DO extends AuditingEntity, DTO extends AuditingEntityDTO>
{
    <REPO extends AuditingEntityRepository<DO>> REPO getRepository();

    <MAPPER extends AuditingEntityMapper<DO, DTO>> MAPPER getMapper();

    DTO save(DTO dto);

    DTO findOne(DTO dto);

    DTO findById(DTO dto);

    List<DTO> findAll();

    List<DTO> findAll(DTO dto);

    Page<DTO> findAll(DTO dto, Pageable pageable);

    long count();

    long count(DTO dto);

    void activate(DTO dto);

    void freeze(DTO dto);

    void delete(DTO dto);

    void deleteAll();
}
