package cn.lb.auth.domain.service.impl;

import cn.lb.auth.common.enums.IsDeletedFlagEnum;
import cn.lb.auth.domain.convert.AuthRoleBOConverter;
import cn.lb.auth.domain.entity.AuthRoleBO;
import cn.lb.auth.domain.service.AuthRoleDomainService;
import cn.lb.auth.infra.basic.entity.AuthRole;
import cn.lb.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Slf4j
@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOTOENTITY(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        int count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOTOENTITY(authRoleBO);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleBO.getId());
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = authRoleService.update(authRole);
        return count > 0;
    }
}
