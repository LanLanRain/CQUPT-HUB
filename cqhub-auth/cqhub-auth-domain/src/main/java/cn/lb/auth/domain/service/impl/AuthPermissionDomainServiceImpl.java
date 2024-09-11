package cn.lb.auth.domain.service.impl;

import cn.lb.auth.domain.entity.AuthPermissionBO;
import cn.lb.auth.domain.service.AuthPermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Slf4j
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {
    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        return null;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        return null;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        return null;
    }

    @Override
    public List<String> getPermission(String userName) {
        return List.of();
    }
}
