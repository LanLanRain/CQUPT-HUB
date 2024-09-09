package cn.lb.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 * @author RainSoul
 * @create 2024-09-08
 */
@Getter
@Setter
public class AuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private String roleKey;

    private String createdBy;

    private Date createdTime;

    private String updateBy;

    private Date updateTime;

    private Integer isDeleted;
}
