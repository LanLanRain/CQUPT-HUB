package cn.lb.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RainSoul
 * @create 2024-09-08
 */
@Data
public class AuthRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private String roleKey;
}
