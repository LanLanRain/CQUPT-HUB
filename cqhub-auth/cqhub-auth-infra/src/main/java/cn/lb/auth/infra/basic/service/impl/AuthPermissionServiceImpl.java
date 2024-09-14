package cn.lb.auth.infra.basic.service.impl;

import cn.lb.auth.infra.basic.entity.AuthPermission;
import cn.lb.auth.infra.basic.service.AuthPermissionService;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-13
 */
public class AuthPermissionServiceImpl implements AuthPermissionService {
    @Override
    public AuthPermission queryById(Long id) {
        return null;
    }

    @Override
    public int insert(AuthPermission authPermission) {
        return 0;
    }

    @Override
    public int update(AuthPermission authPermission) {
        return 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<AuthPermission> queryByRoleList(List<Long> roleIdList) {
        return List.of();
    }
}
