package cn.lb.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限表
 *
 * @author RainSoul
 * @create 2024-09-11
 */
@Data
public class AuthRolePermissionBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private List<Long> permissionIdList;
}
