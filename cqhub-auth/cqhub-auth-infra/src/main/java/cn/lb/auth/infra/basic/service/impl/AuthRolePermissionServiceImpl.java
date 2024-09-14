package cn.lb.auth.infra.basic.service.impl;

import cn.lb.auth.infra.basic.entity.AuthRolePermission;
import cn.lb.auth.infra.basic.service.AuthRolePermissionService;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-13
 */
public class AuthRolePermissionServiceImpl implements AuthRolePermissionService {
    @Override
    public AuthRolePermission queryById(Long id) {
        return null;
    }

    @Override
    public AuthRolePermission insert(AuthRolePermission authRolePermission) {
        return null;
    }

    @Override
    public int batchInsert(List<AuthRolePermission> authRolePermissionList) {
        return 0;
    }

    @Override
    public AuthRolePermission update(AuthRolePermission authRolePermission) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission) {
        return List.of();
    }
}
