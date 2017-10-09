package org.yframework.mybatis.auditing.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.yframework.mybatis.auditing.service.AuditingEntityService;

import java.io.Serializable;
import java.util.List;

/**
 * Description: AuditingEntityResource.<br>
 * Date: 2017/9/9 11:41<br>
 * Author: ysj
 */
public interface AuditingEntityResource<VO extends Object>
{
    <SERVICE extends AuditingEntityService> SERVICE getService();

    ResponseEntity<VO> create(VO vo) throws Exception;

    ResponseEntity<VO> update(VO vo) throws Exception;

    List<VO> getAll();

    <ID extends Serializable> ResponseEntity<VO> get(ID id) throws Exception;

    ResponseEntity<Page<VO>> query(String query, Pageable pageable) throws Exception;

    <ID extends Serializable> ResponseEntity<ID> activate(ID id) throws Exception;

    <ID extends Serializable> ResponseEntity<ID> freeze(ID id) throws Exception;

    <ID extends Serializable> ResponseEntity<ID> delete(ID id) throws Exception;
}
