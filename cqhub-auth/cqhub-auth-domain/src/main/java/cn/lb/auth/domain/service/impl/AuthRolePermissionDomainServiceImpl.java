package cn.lb.auth.domain.service.impl;

import cn.lb.auth.common.enums.IsDeletedFlagEnum;
import cn.lb.auth.domain.entity.AuthRolePermissionBO;
import cn.lb.auth.domain.service.AuthRolePermissionDomainService;
import cn.lb.auth.infra.basic.entity.AuthRolePermission;
import cn.lb.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-16
 */
@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {
    @Resource
    private AuthRolePermissionService authRolePermissionService;

    /**
     * 为角色添加权限
     *
     * @param authRolePermissionBO 角色权限信息封装类，包含角色ID和权限ID列表
     * @return 如果成功添加（即插入数据库），返回true；否则返回false
     */
    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        // 初始化角色权限列表，用于批量插入数据库
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();

        // 获取角色ID，用于后续关联权限
        Long roleId = authRolePermissionBO.getRoleId();

        // 遍历权限ID列表，为每个权限ID创建角色权限对象
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            // 设置角色权限关联的角色ID
            authRolePermission.setRoleId(roleId);
            // 设置角色权限关联的权限ID
            authRolePermission.setPermissionId(permissionId);
            // 设置是否删除状态为未删除，标记该角色权限是有效的
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

            // 将创建的角色权限对象添加到列表中
            rolePermissionList.add(authRolePermission);
        });

        // 批量插入角色权限数据到数据库
        int count = authRolePermissionService.batchInsert(rolePermissionList);

        // 返回插入结果，若插入数量大于0，表示添加成功
        return count > 0;
    }

}
