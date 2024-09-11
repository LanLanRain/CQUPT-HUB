package cn.lb.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限表
 *
 * @author RainSoul
 * @create 2024-09-11
 */
@Data
public class AuthPermissionBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long parentId;

    private Integer type;

    private String menuUrl;

    private Integer status;

    private Integer show;

    private String icon;

    private String permissionKey;
}
