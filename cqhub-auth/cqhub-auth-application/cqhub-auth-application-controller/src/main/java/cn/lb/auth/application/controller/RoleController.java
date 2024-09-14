package cn.lb.auth.application.controller;

import cn.lb.auth.application.convert.AuthRoleDTOConverter;
import cn.lb.auth.application.dto.AuthRoleDTO;
import cn.lb.auth.domain.entity.AuthRoleBO;
import cn.lb.auth.domain.service.AuthRoleDomainService;
import cn.lb.auth.entity.Result;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RainSoul
 * @create 2024-09-14
 */
@Slf4j
@RestController
@RequestMapping("/role/")
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;

    @RequestMapping("add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(StringUtils.isNotBlank(authRoleDTO.getRoleKey()), "角色标识不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authRoleDTO.getRoleName()), "角色名称不能为空");

            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOTOBO(authRoleDTO);
            return Result.success(authRoleDomainService.add(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.add.error", e);
            return Result.fail("添加角色失败");
        }
    }

    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOTOBO(authRoleDTO);
            return Result.success(authRoleDomainService.delete(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.delete.error:{}", e);
            return Result.fail("删除角色失败");
        }
    }

    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }

            Preconditions.checkNotNull(authRoleDTO.getId(), "角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.convertDTOTOBO(authRoleDTO);
            return Result.success(authRoleDomainService.update(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.update.error", e);
            return Result.fail("更新角色失败");
        }
    }
}
