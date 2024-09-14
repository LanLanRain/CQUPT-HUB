package cn.lb.auth.application.convert;

import cn.lb.auth.application.dto.AuthRoleDTO;
import cn.lb.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Mapper
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO convertDTOTOBO(AuthRoleDTO authRoleDTO);


}
