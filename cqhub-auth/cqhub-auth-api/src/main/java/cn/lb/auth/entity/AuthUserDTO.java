package cn.lb.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Data
public class AuthUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String nickName;

    private String email;

    private String phone;

    private String password;

    private Integer sex;

    private String avatar;

    private Integer status;

    private String introduce;

    private String extJson;
}
