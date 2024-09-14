package cn.lb.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.lb.auth.common.enums.AuthUserStatusEnum;
import cn.lb.auth.common.enums.IsDeletedFlagEnum;
import cn.lb.auth.domain.constants.AuthConstant;
import cn.lb.auth.domain.convert.AuthUserBOConverter;
import cn.lb.auth.domain.entity.AuthUserBO;
import cn.lb.auth.domain.redis.RedisUtil;
import cn.lb.auth.domain.service.AuthUserDomainService;
import cn.lb.auth.infra.basic.entity.AuthRole;
import cn.lb.auth.infra.basic.entity.AuthRolePermission;
import cn.lb.auth.infra.basic.entity.AuthUser;
import cn.lb.auth.infra.basic.entity.AuthUserRole;
import cn.lb.auth.infra.basic.service.AuthRolePermissionService;
import cn.lb.auth.infra.basic.service.AuthRoleService;
import cn.lb.auth.infra.basic.service.AuthUserRoleService;
import cn.lb.auth.infra.basic.service.AuthUserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Slf4j
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    private static final String salt = "salt";

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;


    private String authRolePrefix = "auth.role";

    private static final String LOGIN_PREFIX = "loginCode";

    private String authPermissionPrefix = "auth.permission";

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        // 检查用户是否已经存在
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        List<AuthUser> existUser = authUserService.queryByCondition(existAuthUser);
        if (existUser.size() > 0) {
            // 如果用户已经存在，返回true
            return true;
        }
        // 将用户注册信息的业务对象转换为实体对象
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOTOENTITY(authUserBO);
        // 对用户密码进行加密处理
        if (StringUtils.isNotBlank(authUser.getPassword())) {
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        }
        // 设置默认头像
        if (StringUtils.isBlank(authUser.getAvatar())) {
            authUser.setAvatar("https://img2.baidu.com/it/u=2266487739,2878951669&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        }
        // 设置用户状态为启用，标记用户未删除
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        // 插入用户信息到数据库
        Integer count = authUserService.insert(authUser);

        // 为新用户分配默认角色
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole role = authRoleService.queryByCondition(authRole);
        // 构建用户与角色的关系并插入数据库
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(role.getId());
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        // 将用户的角色信息缓存到Redis
        String rolekey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> authRoleList = new LinkedList<>();
        authRoleList.add(role);
        redisUtil.set(rolekey, new Gson().toJson(authRoleList));

        // 将用户的权限信息缓存到Redis
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(role.getId());
        List<AuthRolePermission> authRolePermissions = authRolePermissionService.queryByCondition(authRolePermission);
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(authRolePermissions));

        // 返回插入操作是否成功
        return count > 0;
    }


    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOTOENTITY(authUserBO);
        Integer count = authUserService.updateByUserName(authUser);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    /**
     * 本方法通过验证码进行登录，首先从Redis中获取与验证码关联的用户标识（openId），
     * 如果验证码无效或过期，则返回null表示登录失败。
     * 如果验证码有效，则使用openId创建一个AuthUserBO对象，调用register方法注册用户，
     * 并使用Sa-Token库的StpUtil类进行登录，最后返回登录后的Token信息。
     *
     * @param validCode 验证码，用于验证用户身份
     * @return 登录成功后返回SaTokenInfo对象，包含Token相关信息；登录失败返回null
     */
    @Override
    public SaTokenInfo doLogin(String validCode) {
        // 构建登录验证的Redis键
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        // 从Redis中获取与验证码关联的openId
        String openId = redisUtil.get(loginKey);
        // 如果openId为空或空白，表示验证码无效或已过期，返回null
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        // 创建AuthUserBO对象，设置用户名称为openId
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        // 注册用户
        register(authUserBO);
        // 使用Sa-Token进行登录
        StpUtil.login(openId);
        // 获取登录后的Token信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 返回Token信息
        return tokenInfo;
    }


    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        // 根据传入的AuthUserBO对象查询用户信息
        // 首先创建一个AuthUser对象，用于查询条件的封装
        AuthUser authUser = new AuthUser();
        // 设置查询条件，即用户名
        authUser.setUserName(authUserBO.getUserName());
        // 调用服务层方法，根据条件查询用户列表
        List<AuthUser> userList = authUserService.queryByCondition(authUser);
        // 检查查询结果是否为空
        if(CollectionUtils.isEmpty(userList)){
            // 如果用户列表为空，返回一个新的空AuthUserBO对象
            return new AuthUserBO();
        }
        // 获取查询结果中的第一个用户
        AuthUser user = userList.get(0);
        // 将用户实体类转换为业务对象（BO），并返回
        return AuthUserBOConverter.INSTANCE.convertENTITYTOBO(user);
    }
}
