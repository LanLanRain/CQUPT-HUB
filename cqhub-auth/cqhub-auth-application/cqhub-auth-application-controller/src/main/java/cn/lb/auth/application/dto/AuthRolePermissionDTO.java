package cn.lb.auth.application.dto;

import lombok.Data;

import java.util.List;

/**
 * @description: 角色权限关联表
 * @author RainSoul
 * @create 2024-09-08
 */
@Data
public class AuthRolePermissionDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private List<Long> permissionIdList;
}
