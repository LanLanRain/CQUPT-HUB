package cn.lb.auth.application.convert;

import cn.lb.auth.domain.entity.AuthUserBO;
import cn.lb.auth.entity.AuthUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertDTOTOBO(AuthUserDTO authUserDTO);

    AuthUserDTO convertBOTODTO(AuthUserBO authUserBO);

}
