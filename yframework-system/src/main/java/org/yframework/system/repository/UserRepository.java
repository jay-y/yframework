package org.yframework.system.repository;

import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;
import org.yframework.system.domain.User;

/**
 * Description: UserRepository.<br>
 * Date: 2017/9/6 19:56<br>
 * Author: ysj
 */
@Mapper
public interface UserRepository extends AuditingEntityRepository<User>
{
    User findOneByLogin(User entity);
}
