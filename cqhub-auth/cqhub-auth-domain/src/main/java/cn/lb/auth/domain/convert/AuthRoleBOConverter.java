package cn.lb.auth.domain.convert;

import cn.lb.auth.domain.entity.AuthRoleBO;
import cn.lb.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Mapper
public interface AuthRoleBOConverter {

    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole convertBOTOENTITY(AuthRoleBO authRoleBO);
}
