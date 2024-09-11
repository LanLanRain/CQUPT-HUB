package cn.lb.auth.domain.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.lb.auth.domain.entity.AuthUserBO;
import cn.lb.auth.domain.service.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Slf4j
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Override
    public Boolean register(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        return null;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        return null;
    }
}
