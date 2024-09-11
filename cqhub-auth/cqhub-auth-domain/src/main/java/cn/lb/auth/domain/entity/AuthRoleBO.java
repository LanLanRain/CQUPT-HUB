package cn.lb.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Data
public class AuthRoleBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private String roleKey;

}
