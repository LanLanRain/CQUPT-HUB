package cn.lb.auth.application.convert;

import cn.lb.auth.application.dto.AuthRolePermissionDTO;
import cn.lb.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Mapper
public interface AuthRolePermissionDTOConverter {

    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDTOTOBO(AuthRolePermissionDTO authRolePermissionDTO);

}
