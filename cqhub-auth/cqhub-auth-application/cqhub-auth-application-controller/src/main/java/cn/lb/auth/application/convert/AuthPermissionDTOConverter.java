package cn.lb.auth.application.convert;

import cn.lb.auth.application.dto.AuthPermissionDTO;
import cn.lb.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO convertDTOTOBO(AuthPermissionDTO authPermissionDTO);
}
