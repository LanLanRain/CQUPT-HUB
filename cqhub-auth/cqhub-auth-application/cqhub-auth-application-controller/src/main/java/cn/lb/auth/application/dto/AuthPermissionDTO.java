package cn.lb.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RainSoul
 * @create 2024-09-08
 */
@Data
public class AuthPermissionDTO implements Serializable {

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
