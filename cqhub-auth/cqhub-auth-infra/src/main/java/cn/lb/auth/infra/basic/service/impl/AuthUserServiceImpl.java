package cn.lb.auth.infra.basic.service.impl;

import cn.lb.auth.infra.basic.entity.AuthUser;
import cn.lb.auth.infra.basic.mapper.AuthUserDao;
import cn.lb.auth.infra.basic.service.AuthUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-13
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Resource
    private AuthUserDao authUserDao;

    @Override
    public AuthUser queryById(Long id) {
        return authUserDao.queryById(id);
    }

    @Override
    public Integer insert(AuthUser authUser) {
        return authUserDao.insert(authUser);
    }

    @Override
    public Integer update(AuthUser authUser) {
        return 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return authUserDao.deleteById(id) > 0;
    }

    @Override
    public List<AuthUser> queryByCondition(AuthUser authUser) {
        return authUserDao.queryAllByLimit(authUser);
    }

    @Override
    public Integer updateByUserName(AuthUser authUser) {
        return authUserDao.updateByUserName(authUser);
    }
}
