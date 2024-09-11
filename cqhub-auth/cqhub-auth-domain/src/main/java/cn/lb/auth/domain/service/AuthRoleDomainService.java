package cn.lb.auth.domain.service;

import cn.lb.auth.domain.entity.AuthRoleBO;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
public interface AuthRoleDomainService {

    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);

}
