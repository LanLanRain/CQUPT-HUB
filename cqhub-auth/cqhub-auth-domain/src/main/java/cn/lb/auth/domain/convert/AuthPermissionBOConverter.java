package cn.lb.auth.domain.convert;

import cn.lb.auth.domain.entity.AuthPermissionBO;
import cn.lb.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Mapper
public interface AuthPermissionBOConverter {

    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    AuthPermission convertBOTOENTITY(AuthPermissionBO authPermissionBO);

}
