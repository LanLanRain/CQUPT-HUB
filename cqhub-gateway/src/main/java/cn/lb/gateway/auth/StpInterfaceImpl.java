package cn.lb.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.lb.gateway.entity.AuthPermission;
import cn.lb.gateway.entity.AuthRole;
import cn.lb.gateway.redis.RedisUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.Resource;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户权限相关的方法实现类
 * 该类实现了StpInterface接口，主要用于提供权限和角色的相关信息
 *
 * @author RainSoul
 * @create 2024-09-08
 */
public class StpInterfaceImpl implements StpInterface {
    // 注入Redis工具类资源
    @Resource
    private RedisUtil redisUtil;

    // 权限相关键的前缀
    private String authPermissionPrefix = "auth.permission";

    // 角色相关键的前缀
    private String authRolePrefix = "auth.role";

    /**
     * 根据登录ID和登录类型获取权限列表
     *
     * @param loginId 登录用户的ID
     * @param loginType 登录类型
     * @return 权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 调用通用的权限获取方法，传入权限前缀
        return getAuth(loginId.toString(), authPermissionPrefix);
    }

    /**
     * 根据登录ID和登录类型获取角色列表
     *
     * @param loginId 登录用户的ID
     * @param loginType 登录类型
     * @return 角色列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 调用通用的角色获取方法，传入角色前缀
        return getAuth(loginId.toString(), authRolePrefix);
    }

    /**
     * 通用的权限或角色信息获取方法
     *
     * @param loginId 登录用户的ID，用于构建Redis键
     * @param prefix Redis键的前缀，区分权限或角色
     * @return 权限或角色的列表
     */
    private List<String> getAuth(String loginId, String prefix) {
        // 构建Redis的键
        String authKey = redisUtil.buildKey(prefix, loginId);
        // 从Redis获取相应的值
        String authValue = (String) redisUtil.get(authKey);
        // 如果值为空或空白，则返回空列表
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }
        // 使用链表存储权限或角色信息
        List<String> authList = new LinkedList<>();
        // 根据前缀判断是角色还是权限信息，并相应处理
        if (authRolePrefix.equals(prefix)) {
            // 将角色信息从JSON字符串转换为对象列表
            List<AuthRole> roleList = new Gson().fromJson(authValue, new TypeToken<List<AuthRole>>() {}.getType());
            // 将角色列表转换为角色键的列表
            authList = roleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        } else if (authPermissionPrefix.equals(prefix)) {
            // 将权限信息从JSON字符串转换为对象列表
            List<AuthPermission> permissionList = new Gson().fromJson(authValue, new TypeToken<List<AuthPermission>>() {}.getType());
            // 将权限列表转换为权限键的列表
            authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        }
        // 返回处理后的列表
        return authList;
    }
}


