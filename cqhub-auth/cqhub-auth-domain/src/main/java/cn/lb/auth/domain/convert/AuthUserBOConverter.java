package cn.lb.auth.domain.convert;

import cn.lb.auth.domain.entity.AuthUserBO;
import cn.lb.auth.infra.basic.entity.AuthUser;
import org.mapstruct.factory.Mappers;

/**
 * @author RainSoul
 * @create 2024-09-12
 */
public interface AuthUserBOConverter {

    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser convertBOTOENTITY(AuthUserBO authUserBO);

    AuthUserBO convertENTITYTOBO(AuthUser authUser);
}
