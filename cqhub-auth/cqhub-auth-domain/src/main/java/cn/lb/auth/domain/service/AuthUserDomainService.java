package cn.lb.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.lb.auth.domain.entity.AuthUserBO;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
public interface AuthUserDomainService {

    /**
     * 注册
     */
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     */
    Boolean update(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     */
    Boolean delete(AuthUserBO authUserBO);

    /**
     * 登录
     */
    SaTokenInfo doLogin(String validCode);

    /**
     * 获取用户信息
     */
    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
