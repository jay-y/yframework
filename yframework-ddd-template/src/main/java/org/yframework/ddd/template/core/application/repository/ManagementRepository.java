package org.yframework.ddd.template.core.application.repository;

import org.apache.ibatis.annotations.Mapper;
import org.yframework.ddd.template.core.domain.model.Management;
import org.yframework.ddd.template.si.persistence.mybatis.adapter.CRUDRepositoryAdapter;

/**
 * Description: 管理资源库.<br>
 * Date: 2018/10/10 下午7:58<br>
 * Author: ysj
 */
@Mapper
public interface ManagementRepository extends CRUDRepositoryAdapter<Management>
{
}
