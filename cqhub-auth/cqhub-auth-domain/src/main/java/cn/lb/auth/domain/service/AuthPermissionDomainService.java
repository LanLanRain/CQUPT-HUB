package cn.lb.auth.domain.service;

import cn.lb.auth.domain.entity.AuthPermissionBO;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
public interface AuthPermissionDomainService {

    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String userName);
}
