package com.cust.system.repository;

import com.cust.system.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.yframework.mybatis.auditing.repository.AuditingEntityRepository;

/**
 * Description: UserRepository.<br>
 * Date: 2017/9/6 19:56<br>
 * Author: ysj
 */
@Mapper
public interface UserRepository extends AuditingEntityRepository<User>
{
    User findUserByLogin(String login);

    User findUserByEmail(String email);

    User findUserById(String id);
}
